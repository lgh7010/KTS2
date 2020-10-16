package com.krafton.kts.backend.testcase.service.internal;

import com.krafton.kts.backend.common.JdbcCommon;
import com.krafton.kts.backend.testcase.domain.db.KTS_TESTCASE;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddTestcaseServiceJDBC extends JdbcCommon implements AddTestcaseService {
    public AddTestcaseServiceJDBC(DataSource dataSource) {
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
}
