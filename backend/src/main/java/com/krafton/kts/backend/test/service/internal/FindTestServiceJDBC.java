package com.krafton.kts.backend.test.service.internal;

import com.krafton.kts.backend.common.JdbcCommon;
import com.krafton.kts.backend.test.domain.db.KTS_TEST;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FindTestServiceJDBC extends JdbcCommon implements FindTestService {
    public FindTestServiceJDBC(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public KTS_TEST findTest(int testSeq) {
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
