package com.krafton.kts.backend.testcase.repository;

import com.krafton.kts.backend.common.JdbcCommon;
import com.krafton.kts.backend.testcase.domain.KTS_TESTCASE;

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
    public List<KTS_TESTCASE> findTestcasesByTEST_SEQ(int TEST_SEQ) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM KTS_TESTCASE WHERE TESTCASE_SEQ IN (" +
                    "SELECT TESTCASE_SEQ FROM TEST_REL_TESTCASE WHERE TEST_SEQ = ?" +
                    ")");
            pstmt.setInt(1, TEST_SEQ);
            rs = pstmt.executeQuery();

            List<KTS_TESTCASE> list = new ArrayList<>();
            while(rs.next()){
                KTS_TESTCASE tc = new KTS_TESTCASE();
                tc.setTESTCASE_SEQ(rs.getInt("TESTCASE_SEQ"));
                tc.setNAME(rs.getString("NAME"));
                tc.setDESCRIPTION(rs.getString("DESCRIPTION"));
                tc.setDELETED(rs.getString("DELETED"));

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
    public void removeTestcase(int TESTCASE_SEQ) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);//트랜잭션 처리

            //step 1. TEST_REL_TESTCASE 에서 해당 TESTCASE_SEQ를 가지는 로우 삭제 처리
            pstmt = conn.prepareStatement("UPDATE TEST_REL_TESTCASE SET DELETED = 'Y' WHERE TESTCASE_SEQ = ?");
            pstmt.setInt(1, TESTCASE_SEQ);
            pstmt.executeUpdate();

            //step 2. KTS_ACTION과 KTS_PROPERTY를 조인하여, 현재 삭제해야 하는 TESTCASE_SEQ에 해당하는 ACTION들에 포함된 PROPERTY들의 PROPERTY_SEQ 리스트 확보
            pstmt = conn.prepareStatement("SELECT PROPERTY_SEQ FROM KTS_PROPERTY WHERE ACTION_GUID IN (" +
                    "SELECT ACTION_GUID FROM KTS_ACTION WHERE TESTCASE_SEQ = ? AND DELETED = 'N' GROUP BY ACTION_GUID)");
            pstmt.setInt(1, TESTCASE_SEQ);
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
            pstmt = conn.prepareStatement("UPDATE KTS_PROPERTY SET DELETED = 'Y' WHERE PROPERTY_SEQ IN (" + builder.toString() + ")");
            pstmt.executeUpdate();

            //step 4. KTS_ACTION에서 해당 TESTCASE_SEQ를 가지는 로우 삭제 처리
            pstmt = conn.prepareStatement("UPDATE KTS_ACTION SET DELETED = 'Y' WHERE TESTCASE_SEQ = ?");
            pstmt.setInt(1, TESTCASE_SEQ);
            pstmt.executeUpdate();

            //step 5. KTS_TESTCASE에서 해당 테스트케이스 삭제
            pstmt = conn.prepareStatement("UPDATE KTS_TESTCASE SET DELETED = 'Y' WHERE TESTCASE_SEQ = ?");
            pstmt.setInt(1, TESTCASE_SEQ);
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
    public KTS_TESTCASE findTestcase(int TESTCASE_SEQ) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM KTS_TESTCASE WHERE TESTCASE_SEQ = ? AND DELETED = 'N'");
            pstmt.setInt(1, TESTCASE_SEQ);
            rs = pstmt.executeQuery();

            rs.next();
            KTS_TESTCASE tc = new KTS_TESTCASE();
            tc.setTESTCASE_SEQ(rs.getInt("TESTCASE_SEQ"));
            tc.setNAME(rs.getString("NAME"));
            tc.setDESCRIPTION(rs.getString("DESCRIPTION"));
            tc.setDELETED(rs.getString("DELETED"));
            return tc;
        } catch (Exception e){
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public void updateTestcase(KTS_TESTCASE tc) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("UPDATE KTS_TESTCASE SET NAME = ?, DESCRIPTION = ? WHERE TESTCASE_SEQ = ?");
            pstmt.setString(1, tc.getNAME());
            pstmt.setString(2, tc.getDESCRIPTION());
            pstmt.setInt(3, tc.getTESTCASE_SEQ());
            pstmt.executeUpdate();
        } catch (Exception e){
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt);
        }
    }

    @Override
    public Optional<KTS_TESTCASE> findTestcaseByTESTCASE_SEQ(int TESTCASE_SEQ) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select * from KTS_TESTCASE where TESTCASE_SEQ = ? and DELETED = 'N'");
            pstmt.setInt(1, TESTCASE_SEQ);
            rs = pstmt.executeQuery();

            if(rs.next()){
                KTS_TESTCASE tc = new KTS_TESTCASE();
                tc.setTESTCASE_SEQ(rs.getInt("TESTCASE_SEQ"));
                tc.setNAME(rs.getString("NAME"));
                tc.setDESCRIPTION(rs.getString("DESCRIPTION"));
                return Optional.of(tc);
            } else {
                Optional.empty();
            }
        } catch (Exception e){
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }

        return Optional.empty();
    }

    @Override
    public List<KTS_TESTCASE> findAllTestcase() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select * from KTS_TESTCASE where DELETED = 'N'");
            rs = pstmt.executeQuery();

            List<KTS_TESTCASE> testcases = new ArrayList<>();
            while(rs.next()){
                KTS_TESTCASE test = new KTS_TESTCASE();
                test.setTESTCASE_SEQ(rs.getInt("TESTCASE_SEQ"));
                test.setNAME(rs.getString("NAME"));
                test.setDESCRIPTION(rs.getString("DESCRIPTION"));
                test.setDELETED(rs.getString("DELETED"));

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
