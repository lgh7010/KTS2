package com.krafton.kts.backend.domain.testcase.service.impl.jdbc;

import com.krafton.kts.backend.common.JdbcCommon;
import com.krafton.kts.backend.domain.testcase.domain.command.RemoveTestcaseCommand;
import com.krafton.kts.backend.domain.testcase.domain.db.KTS_TESTCASE;
import com.krafton.kts.backend.domain.testcase.service.impl.TestcaseInterface;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestcaseInterfaceJDBC extends JdbcCommon implements TestcaseInterface {
    public TestcaseInterfaceJDBC(DataSource dataSource) {
        super(dataSource);
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
    public List<KTS_TESTCASE> findAll() {
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

            //step 2. KTS_ACTION과 KTS_ACTION_PROPERTY를 조인하여, 현재 삭제해야 하는 TESTCASE_GUID에 해당하는 ACTION들에 포함된 PROPERTY들의 PROPERTY_SEQ 리스트 확보
            pstmt = conn.prepareStatement(
            "UPDATE KTS_ACTION_PROPERTY SET deleted = 'Y' " +
                "WHERE propertySeq IN (SELECT * FROM (" +
                "   SELECT propertySeq FROM KTS_ACTION_PROPERTY WHERE actionGuid IN (" +
                "       SELECT actionGuid FROM KTS_ACTION WHERE testcaseGuid = ? AND deleted = 'N' GROUP BY actionGuid" +
                "    ) GROUP BY propertySeq" +
                ") AS temp)");
            pstmt.setString(1, command.getTestcaseGuid());
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
}
