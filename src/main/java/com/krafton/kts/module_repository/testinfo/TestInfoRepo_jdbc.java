package com.krafton.kts.module_repository.testinfo;

import com.krafton.kts.module_repository.JdbcCommon;
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
    public Optional<KTS_TEST> findTestByTEST_SEQ(int TEST_SEQ) {
        return Optional.empty();
    }

    @Override
    public Optional<KTS_TEST> findTestByNAME(String NAME) {
        return Optional.empty();
    }

    @Override
    public List<KTS_TEST> findAllTest() {
        String sql = "select * from KTS_TEST";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
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
}
