package com.krafton.kts.backend.domain.test_rel_testcase.service.impl;

import com.krafton.kts.backend.common.JdbcCommon;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.TestRelTestcaseSaveCommand;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.db.TEST_REL_TESTCASE;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.db.TestRelTestcaseDerived;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestRelTestcaseInterfaceJDBC extends JdbcCommon implements TestRelTestcaseInterface {
    public TestRelTestcaseInterfaceJDBC(DataSource dataSource) {
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

    @Override
    public void saveTestRelTestcase(TestRelTestcaseSaveCommand command) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);//트랜잭션 처리

            //step 1. 테스트 생성
            int testSeq = command.getTestSeq();
            if(testSeq <= 0){
                pstmt = conn.prepareStatement("INSERT INTO KTS_TEST (name, description) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, command.getTestName());
                pstmt.setString(2, command.getTestDescription());
                pstmt.executeUpdate();

                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        testSeq = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Creating KTS_TEST failed, no testSeq obtained.");
                    }
                }
            } else {
                //테스트 정보 업데이트
                pstmt = conn.prepareStatement("UPDATE KTS_TEST SET name = ?, description = ? WHERE testSeq = ?");
                pstmt.setString(1, command.getTestName());
                pstmt.setString(2, command.getTestDescription());
                pstmt.setInt(3, testSeq);
                pstmt.executeUpdate();
            }

            //step 2. 관계 생성
            if(command.getRelationList().stream().count() > 0){
                String values = "";
                for(int listIndex = 0; listIndex < command.getRelationList().stream().count(); listIndex++){
                    TEST_REL_TESTCASE rel = command.getRelationList().get(listIndex);
                    values += "(" + rel.getRelationSeq() + ", " + testSeq + ", " + listIndex + ", '" + rel.getTestcaseGuid() + "')";
                    if(listIndex < command.getRelationList().stream().count()-1){
                        values += ",";
                    }
                }
                pstmt = conn.prepareStatement("INSERT INTO TEST_REL_TESTCASE (relationSeq, testSeq, listIndex, testcaseGuid) VALUES " + values
                        + " ON DUPLICATE KEY UPDATE listIndex = VALUES(listIndex)");
                pstmt.executeUpdate();
            }

            //step 3. 삭제된 관계 DELETED=Y로 전환
            if(command.getRemoveRelationSeqList() != null && command.getRemoveRelationSeqList().stream().count() > 0){
                StringBuilder builder = new StringBuilder();
                Iterator<Integer> iter = command.getRemoveRelationSeqList().iterator();
                while(iter.hasNext()){
                    builder.append(iter.next());
                    if(iter.hasNext()){
                        builder.append(",");
                    }
                }
                pstmt = conn.prepareStatement("UPDATE TEST_REL_TESTCASE SET deleted = 'Y' WHERE relationSeq IN (" + builder.toString() + ")");
                pstmt.executeUpdate();
            }

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
}
