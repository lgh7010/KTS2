package com.krafton.kts.backend.test.repository;

import com.krafton.kts.backend.common.JdbcCommon;
import com.krafton.kts.backend.test.domain.KTS_TEST;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepoJdbc_test extends JdbcCommon implements Repo_test {

    public RepoJdbc_test(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public KTS_TEST saveTest(KTS_TEST test) {
        return null;
    }

    @Override
    public void removeTest(int testSeq) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);//트랜잭션 처리

            //step 1. KTS_TEST 테이블에서 해당 테스트의 DELETED = Y로 변경
            pstmt = conn.prepareStatement("update KTS_TEST set deleted = 'Y' where testSeq = ?");
            pstmt.setInt(1, testSeq);
            pstmt.executeUpdate();

            //step 2. TEST_REL_TESTCASE 테이블에서 해당하는 TEST_SEQ의 모든 관계내역의 DELETED = Y로 변경
            pstmt = conn.prepareStatement("update TEST_REL_TESTCASE set deleted = 'Y' where testSeq = ?");
            pstmt.setInt(1, testSeq);
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
    public Optional<KTS_TEST> findTestByTEST_SEQ(int testSeq) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select * from KTS_TEST where testSeq = ? AND deleted = 'N'");
            pstmt.setInt(1, testSeq);
            rs = pstmt.executeQuery();

            rs.next();

            KTS_TEST test = new KTS_TEST();
            test.setTestSeq(rs.getInt("testSeq"));
            test.setName(rs.getString("name"));
            test.setDescription(rs.getString("description"));
            test.setDeleted(rs.getString("deleted"));

            return Optional.of(test);
        } catch (Exception e){
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<KTS_TEST> findTestByNAME(String name) {
        return Optional.empty();
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
                test.setTestSeq(rs.getInt("testSeq"));
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
}
