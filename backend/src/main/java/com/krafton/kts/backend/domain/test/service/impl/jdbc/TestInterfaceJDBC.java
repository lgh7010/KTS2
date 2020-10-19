package com.krafton.kts.backend.domain.test.service.impl.jdbc;

import com.krafton.kts.backend.common.JdbcCommon;
import com.krafton.kts.backend.domain.test.domain.command.AddTestCommand;
import com.krafton.kts.backend.domain.test.domain.command.RemoveTestCommand;
import com.krafton.kts.backend.domain.test.domain.db.KTS_TEST;
import com.krafton.kts.backend.domain.test.service.impl.TestInterface;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestInterfaceJDBC extends JdbcCommon implements TestInterface {
    public TestInterfaceJDBC(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public KTS_TEST findTest(String testGuid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select * from KTS_TEST where testGuid = ? AND deleted = 'N'");
            pstmt.setString(1, testGuid);
            rs = pstmt.executeQuery();

            rs.next();

            KTS_TEST test = new KTS_TEST();
            test.setTestGuid(rs.getString("testGuid"));
            test.setName(rs.getString("name"));
            test.setDescription(rs.getString("description"));
            test.setDeleted(rs.getString("deleted"));

            return test;
        } catch (Exception e){
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<KTS_TEST> findAllTest() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select * from KTS_TEST where deleted = 'N'");
            rs = pstmt.executeQuery();

            List<KTS_TEST> tests = new ArrayList<>();
            while(rs.next()){
                KTS_TEST test = new KTS_TEST();
                test.setTestGuid(rs.getString("testGuid"));
                test.setName(rs.getString("name"));
                test.setDescription(rs.getString("description"));
                test.setDeleted(rs.getString("deleted"));

                tests.add(test);
            }

            return tests;
        } catch (Exception e){
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public void removeTest(RemoveTestCommand command) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);//트랜잭션 처리

            //step 1. KTS_TEST 테이블에서 해당 테스트의 DELETED = Y로 변경
            pstmt = conn.prepareStatement("update KTS_TEST set deleted = 'Y' where testGuid = ?");
            pstmt.setString(1, command.getTestGuid());
            pstmt.executeUpdate();

            //step 2. TEST_REL_TESTCASE 테이블에서 해당하는 TEST_SEQ의 모든 관계내역의 DELETED = Y로 변경
            pstmt = conn.prepareStatement("update TEST_REL_TESTCASE set deleted = 'Y' where testGuid = ?");
            pstmt.setString(1, command.getTestGuid());
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
    public void addTest(AddTestCommand command) {

    }
}
