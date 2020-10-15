package com.krafton.kts.backend.testcase.repository;

import com.krafton.kts.backend.common.JdbcCommon;
import com.krafton.kts.backend.testcase.domain.KTS_TESTCASE;
import com.krafton.kts.backend.testcase.domain.RemoveTestcaseCommand;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class RepoJdbc_testcase extends JdbcCommon implements Repo_testcase {

    public RepoJdbc_testcase(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<KTS_TESTCASE> findTestcasesByTEST_SEQ(int testSeq) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM KTS_TESTCASE WHERE testcaseGuid IN (" +
                    "SELECT testcaseGuid FROM TEST_REL_TESTCASE WHERE testSeq = ?" +
                    ")");
            pstmt.setInt(1, testSeq);
            rs = pstmt.executeQuery();

            List<KTS_TESTCASE> list = new ArrayList<>();
            while(rs.next()){
                KTS_TESTCASE tc = new KTS_TESTCASE();
                tc.setTestcaseGuid(rs.getString("testcaseGuid"));
                tc.setName(rs.getString("name"));
                tc.setDescription(rs.getString("description"));
                tc.setDeleted(rs.getString("deleted"));

                list.add(tc);
            }
            return list;
        } catch (Exception e){
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public void removeTestcase(RemoveTestcaseCommand command) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);//트랜잭션 처리

            //step 1. TEST_REL_TESTCASE 에서 해당 TESTCASE_GUID를 가지는 로우 삭제 처리
            pstmt = conn.prepareStatement("UPDATE TEST_REL_TESTCASE SET deleted = 'Y' WHERE testcaseGuid = ?");
            pstmt.setString(1, command.getTestcaseGuid());
            pstmt.executeUpdate();

            //step 2. KTS_ACTION과 KTS_PROPERTY를 조인하여, 현재 삭제해야 하는 TESTCASE_GUID에 해당하는 ACTION들에 포함된 PROPERTY들의 PROPERTY_SEQ 리스트 확보
            pstmt = conn.prepareStatement("SELECT propertySeq FROM KTS_PROPERTY WHERE actionGuid IN (" +
                    "SELECT actionGuid FROM KTS_ACTION WHERE testcaseGuid = ? AND deleted = 'N' GROUP BY actionGuid)");
            pstmt.setString(1, command.getTestcaseGuid());
            ResultSet rs = pstmt.executeQuery();
            List<Integer> propertySeqList = new ArrayList<>();
            while(rs.next()){
                propertySeqList.add(rs.getInt(1));
            }
            StringBuilder builder = new StringBuilder();
            Iterator<Integer> iter = propertySeqList.iterator();
            while(iter.hasNext()){
                builder.append(iter.next());
                if(iter.hasNext()){
                    builder.append(",");
                }
            }

            //step 3. KTS_PROPERTY에서 위에서 구한 SEQ에 해당하는 PROPERTY들 삭제 처리
            pstmt = conn.prepareStatement("UPDATE KTS_PROPERTY SET deleted = 'Y' WHERE propertySeq IN (" + builder.toString() + ")");
            pstmt.executeUpdate();

            //step 4. KTS_ACTION에서 해당 TESTCASE_GUID를 가지는 로우 삭제 처리
            pstmt = conn.prepareStatement("UPDATE KTS_ACTION SET deleted = 'Y' WHERE testcaseGuid = ?");
            pstmt.setString(1, command.getTestcaseGuid());
            pstmt.executeUpdate();

            //step 5. KTS_TESTCASE에서 해당 테스트케이스 삭제
            pstmt = conn.prepareStatement("UPDATE KTS_TESTCASE SET deleted = 'Y' WHERE testcaseGuid = ?");
            pstmt.setString(1, command.getTestcaseGuid());
            pstmt.executeUpdate();

            conn.commit();
        } catch (Exception e){
            try {
                conn.rollback();
            } catch(SQLException ee){
                System.out.println(ee);
            }
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt);
        }
    }

    @Override
    public KTS_TESTCASE findTestcase(String testcaseGuid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM KTS_TESTCASE WHERE testcaseGuid = ? AND deleted = 'N'");
            pstmt.setString(1, testcaseGuid);
            rs = pstmt.executeQuery();

            rs.next();
            KTS_TESTCASE tc = new KTS_TESTCASE();
            tc.setTestcaseGuid(rs.getString("testcaseGuid"));
            tc.setName(rs.getString("name"));
            tc.setDescription(rs.getString("description"));
            tc.setDeleted(rs.getString("deleted"));
            return tc;
        } catch (Exception e){
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public void addTestcase(KTS_TESTCASE testcase) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO KTS_TESTCASE (testcaseGuid, name, description, deleted) VALUES (?, ?, ?, 'N') " +
                    "ON DUPLICATE KEY UPDATE name = VALUES(name), description = VALUES(description)");
            pstmt.setString(1, testcase.getTestcaseGuid());
            pstmt.setString(2, testcase.getName());
            pstmt.setString(3, testcase.getDescription());
            pstmt.executeUpdate();
        } catch (Exception e){
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt);
        }
    }

    @Override
    public List<KTS_TESTCASE> findAllTestcase() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select * from KTS_TESTCASE where deleted = 'N'");
            rs = pstmt.executeQuery();

            List<KTS_TESTCASE> testcases = new ArrayList<>();
            while(rs.next()){
                KTS_TESTCASE test = new KTS_TESTCASE();
                test.setTestcaseGuid(rs.getString("testcaseGuid"));
                test.setName(rs.getString("name"));
                test.setDescription(rs.getString("description"));
                test.setDeleted(rs.getString("deleted"));

                testcases.add(test);
            }

            return testcases;
        } catch (Exception e){
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
}
