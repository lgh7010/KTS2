package com.krafton.kts.backend.domain.test_rel_testcase.interfaces.jdbc;

import com.krafton.kts.backend.common.JdbcCommon;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.RemoveTestRelTestcaseByTestGuidCommand;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.RemoveTestRelTestcaseByTestcaseGuidCommand;
import com.krafton.kts.backend.domain.test_rel_testcase.domain.command.SaveTestRelTestcaseCommand;
import com.krafton.kts.backend.service.crossdomain.db.TEST_REL_TESTCASE_JOIN_TESTCASE;
import com.krafton.kts.backend.domain.test_rel_testcase.interfaces.TestRelTestcaseInterface;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestRelTestcaseInterfaceJDBC extends JdbcCommon implements TestRelTestcaseInterface {
    public TestRelTestcaseInterfaceJDBC(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<TEST_REL_TESTCASE_JOIN_TESTCASE> findTestRelTestcaseJoinTestcase(String testGuid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("SELECT a.*, b.name, b.description " +
                    "FROM TEST_REL_TESTCASE a LEFT JOIN KTS_TESTCASE b ON a.testcaseGuid = b.testcaseGuid " +
                    "WHERE a.testGuid = ? AND a.deleted = 'N' ORDER BY listIndex");
            pstmt.setString(1, testGuid);
            rs = pstmt.executeQuery();
            List<TEST_REL_TESTCASE_JOIN_TESTCASE> rels = new ArrayList<>();
            while(rs.next()){
                TEST_REL_TESTCASE_JOIN_TESTCASE rel = new TEST_REL_TESTCASE_JOIN_TESTCASE();
                rel.setRelationGuid(rs.getString("relationGuid"));
                rel.setTestGuid(rs.getString("testGuid"));
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
    public void saveTestRelTestcase(SaveTestRelTestcaseCommand command) {
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//
//        try {
//            conn = getConnection();
//            conn.setAutoCommit(false);//트랜잭션 처리
//
//            //step 1. 테스트 생성
//            String testGuid = command.getTestGuid();
//            if(true){//if(testGuid <= 0){
//                pstmt = conn.prepareStatement("INSERT INTO KTS_TEST (name, description) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
//                pstmt.setString(1, command.getTestName());
//                pstmt.setString(2, command.getTestDescription());
//                pstmt.executeUpdate();
//
//                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
//                    if (generatedKeys.next()) {
//                        testGuid = generatedKeys.getString(1);
//                    } else {
//                        throw new SQLException("Creating KTS_TEST failed, no testGuid obtained.");
//                    }
//                }
//            } else {
//                //테스트 정보 업데이트
//                pstmt = conn.prepareStatement("UPDATE KTS_TEST SET name = ?, description = ? WHERE testGuid = ?");
//                pstmt.setString(1, command.getTestName());
//                pstmt.setString(2, command.getTestDescription());
//                pstmt.setString(3, testGuid);
//                pstmt.executeUpdate();
//            }
//
//            //step 2. 관계 생성
//            if(command.getRelationList().stream().count() > 0){
//                String values = "";
//                for(int listIndex = 0; listIndex < command.getRelationList().stream().count(); listIndex++){
//                    TEST_REL_TESTCASE rel = command.getRelationList().get(listIndex);
//                    values += "(" + rel.getRelationGuid() + ", '" + testGuid + "', " + listIndex + ", '" + rel.getTestcaseGuid() + "')";
//                    if(listIndex < command.getRelationList().stream().count()-1){
//                        values += ",";
//                    }
//                }
//                pstmt = conn.prepareStatement("INSERT INTO TEST_REL_TESTCASE (relationSeq, testGuid, listIndex, testcaseGuid) VALUES " + values
//                        + " ON DUPLICATE KEY UPDATE listIndex = VALUES(listIndex)");
//                pstmt.executeUpdate();
//            }
//
//            //step 3. 삭제된 관계 DELETED=Y로 전환
//            if(command.getRemoveRelationSeqList() != null && command.getRemoveRelationSeqList().stream().count() > 0){
//                StringBuilder builder = new StringBuilder();
//                Iterator<Integer> iter = command.getRemoveRelationSeqList().iterator();
//                while(iter.hasNext()){
//                    builder.append(iter.next());
//                    if(iter.hasNext()){
//                        builder.append(",");
//                    }
//                }
//                pstmt = conn.prepareStatement("UPDATE TEST_REL_TESTCASE SET deleted = 'Y' WHERE relationGuid IN (" + builder.toString() + ")");
//                pstmt.executeUpdate();
//            }
//
//            conn.commit();
//        } catch (Exception e){
//            try {
//                conn.rollback();
//            } catch(SQLException ee){
//                System.out.println(ee);
//            }
//            throw new IllegalStateException(e);
//        } finally {
//            close(conn, pstmt);
////        }
    }

    @Override
    public void removeTestRelTestcaseByTestcaseGuid(RemoveTestRelTestcaseByTestcaseGuidCommand command) {

    }

    @Override
    public void removeTestRelTestcaseByTestGuid(RemoveTestRelTestcaseByTestGuidCommand command) {

    }
}
