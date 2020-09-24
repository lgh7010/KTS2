package com.krafton.kts.module_repository.testinfo;

import com.krafton.kts.module_repository.JdbcCommon;
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
    public KTS_TEST create(KTS_TEST test) {
        return null;
    }

    @Override
    public Optional<KTS_TEST> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<KTS_TEST> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<KTS_TEST> findAll() {
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
}
