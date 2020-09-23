package com.krafton.kts.repository.testinfo;

import com.krafton.kts.testlist.domain.KTS_TEST;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestInfoRepo_jdbc implements TestInfoRepo {

    private final DataSource dataSource;

    public TestInfoRepo_jdbc(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public KTS_TEST create(KTS_TEST test) {
        System.out.println("create");
        return null;
    }

    @Override
    public Optional<KTS_TEST> findById(int id) {
        System.out.println("findById");
        return Optional.empty();
    }

    @Override
    public Optional<KTS_TEST> findByName(String name) {
        System.out.println("findByName");
        return Optional.empty();
    }

    @Override
    public List<KTS_TEST> findAll() {
        System.out.println("findAll");

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







    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
