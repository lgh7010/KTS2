package com.krafton.kts.module_repository.testinfo;

import com.krafton.kts.module_repository.JdbcCommon;
import com.krafton.kts.module_repository.testinfo.domain.TEST_REL_TESTCASE;
import com.krafton.kts.module_testcaselist.domain.KTS_TESTCASE;
import com.krafton.kts.module_testlist.domain.KTS_TEST;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestInfoRepo_jdbc extends JdbcCommon implements TestInfoRepo {

    public TestInfoRepo_jdbc(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public KTS_TEST saveTest(KTS_TEST test) {
        return null;
    }

    @Override
    public void removeTest(int TEST_SEQ) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);//트랜잭션 처리

            //step 1. KTS_TEST 테이블에서 해당 테스트의 DELETED = Y로 변경
            pstmt = conn.prepareStatement("update KTS_TEST set DELETED = 'Y' where TEST_SEQ = ?");
            pstmt.setInt(1, TEST_SEQ);
            pstmt.executeUpdate();

            //step 2. TEST_REL_TESTCASE 테이블에서 해당하는 TEST_SEQ의 모든 관계내역의 DELETED = Y로 변경
            pstmt = conn.prepareStatement("update TEST_REL_TESTCASE set DELETED = 'Y' where TEST_SEQ = ?");
            pstmt.setInt(1, TEST_SEQ);
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
    public Optional<KTS_TEST> findTestByTEST_SEQ(int TEST_SEQ) {
        return Optional.empty();
    }

    @Override
    public Optional<KTS_TEST> findTestByNAME(String NAME) {
        return Optional.empty();
    }

    @Override
    public List<KTS_TEST> findAllTest() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select * from KTS_TEST where DELETED = 'N'");
            rs = pstmt.executeQuery();

            List<KTS_TEST> tests = new ArrayList<>();
            while(rs.next()){
                KTS_TEST test = new KTS_TEST();
                test.setTEST_SEQ(rs.getInt("TEST_SEQ"));
                test.setNAME(rs.getString("NAME"));
                test.setDESCRIPTION(rs.getString("DESCRIPTION"));

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
    public KTS_TESTCASE saveTestcase(KTS_TESTCASE testcase) {
        return null;
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
    public Optional<KTS_TESTCASE> findTestcaseByNAME(String NAME) {
        return Optional.empty();
    }

    @Override
    public List<KTS_TESTCASE> findAllTestcase() {
        return null;
    }

    @Override
    public List<TEST_REL_TESTCASE> findTestRelTestcaseByTEST_SEQ(int TEST_SEQ) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select * from TEST_REL_TESTCASE where TEST_SEQ = ? and deleted = 'N'");
            pstmt.setInt(1, TEST_SEQ);
            rs = pstmt.executeQuery();

            List<TEST_REL_TESTCASE> rels = new ArrayList<>();
            while(rs.next()){
                TEST_REL_TESTCASE rel = new TEST_REL_TESTCASE();
                rel.setRELATION_SEQ(rs.getInt("RELATION_SEQ"));
                rel.setTEST_SEQ(rs.getInt("TEST_SEQ"));
                rel.setLIST_INDEX(rs.getInt("LIST_INDEX"));
                rel.setTESTCASE_SEQ(rs.getInt("TESTCASE_SEQ"));

                rels.add(rel);
            }

            return rels;
        } catch (Exception e){
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<TEST_REL_TESTCASE> findTestRelTestcaseByTESTCASE_SEQ(int TESTCASE_SEQ) {
        return null;
    }
}
