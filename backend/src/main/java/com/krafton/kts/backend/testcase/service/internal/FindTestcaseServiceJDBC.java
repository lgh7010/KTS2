package com.krafton.kts.backend.testcase.service.internal;

import com.krafton.kts.backend.common.JdbcCommon;
import com.krafton.kts.backend.testcase.domain.db.KTS_TESTCASE;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FindTestcaseServiceJDBC extends JdbcCommon implements FindTestcaseService {
    public FindTestcaseServiceJDBC(DataSource dataSource) {
        super(dataSource);
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
}
