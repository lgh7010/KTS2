package com.krafton.kts.backend.testcase.repository;

import com.krafton.kts.backend.common.JdbcCommon;
import com.krafton.kts.backend.testcase.domain.KTS_TESTCASE;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
