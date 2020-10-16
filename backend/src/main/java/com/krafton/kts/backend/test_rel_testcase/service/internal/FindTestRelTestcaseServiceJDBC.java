package com.krafton.kts.backend.test_rel_testcase.service.internal;

import com.krafton.kts.backend.common.JdbcCommon;
import com.krafton.kts.backend.test_rel_testcase.domain.db.TEST_REL_TESTCASE;
import com.krafton.kts.backend.test_rel_testcase.domain.db.TestRelTestcaseDerived;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FindTestRelTestcaseServiceJDBC extends JdbcCommon implements FindTestRelTestcaseService {
    public FindTestRelTestcaseServiceJDBC(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<TestRelTestcaseDerived> findTestRelTestcaseDerived(int testSeq) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("SELECT a.*, b.name, b.description FROM TEST_REL_TESTCASE a LEFT JOIN KTS_TESTCASE b ON a.testcaseGuid = b.testcaseGuid WHERE a.testSeq = ? AND a.deleted = 'N' ORDER BY listIndex");
            pstmt.setInt(1, testSeq);
            rs = pstmt.executeQuery();
            List<TestRelTestcaseDerived> rels = new ArrayList<>();
            while(rs.next()){
                TestRelTestcaseDerived rel = new TestRelTestcaseDerived();
                rel.setRelationSeq(rs.getInt("relationSeq"));
                rel.setTestSeq(rs.getInt("testSeq"));
                rel.setTestcaseGuid(rs.getString("testcaseGuid"));
                rel.setDeleted(rs.getString("deleted"));
                rel.setName(rs.getString("name"));
                rel.setDescription(rs.getString("description"));
                rels.add(rel);
            }
            return rels;
        } catch (Exception e){
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
}
