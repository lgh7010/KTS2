package com.krafton.kts.backend.test_rel_testcase.repository;

import com.krafton.kts.backend.common.JdbcCommon;
import com.krafton.kts.backend.test_rel_testcase.domain.TEST_REL_TESTCASE;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RepoJdbc_test_rel_testcase extends JdbcCommon implements Repo_test_rel_testcase {
    public RepoJdbc_test_rel_testcase(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<TEST_REL_TESTCASE> findTestRelTestcaseByTEST_SEQ(int TEST_SEQ) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select * from TEST_REL_TESTCASE where TEST_SEQ = ? and DELETED = 'N' ORDER BY LIST_INDEX");
            pstmt.setInt(1, TEST_SEQ);
            rs = pstmt.executeQuery();

            List<TEST_REL_TESTCASE> rels = new ArrayList<>();
            while(rs.next()){
                TEST_REL_TESTCASE rel = new TEST_REL_TESTCASE();
                rel.setRELATION_SEQ(rs.getInt("RELATION_SEQ"));
                rel.setTEST_SEQ(rs.getInt("TEST_SEQ"));
                //rel.setLIST_INDEX(rs.getInt("LIST_INDEX"));
                rel.setTESTCASE_GUID(rs.getString("TESTCASE_GUID"));
                rel.setDELETED(rs.getString("DELETED"));

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
    public List<TEST_REL_TESTCASE> findTestRelTestcaseByTESTCASE_GUID(String TESTCASE_GUID) {
        return null;
    }

    @Override
    public void saveTestRelTestcase(List<TEST_REL_TESTCASE> rels, int TEST_SEQ, Boolean createNewTest, String testName, String testDescription, List<Integer> removeRelationSeqList) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);//트랜잭션 처리

            //step 1. 테스트 생성
            if(createNewTest){
                pstmt = conn.prepareStatement("INSERT INTO KTS_TEST (NAME, DESCRIPTION) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, testName);
                pstmt.setString(2, testDescription);
                pstmt.executeUpdate();

                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        TEST_SEQ = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Creating KTS_TEST failed, no TEST_SEQ obtained.");
                    }
                }
            } else {
                //테스트 정보 업데이트
                pstmt = conn.prepareStatement("UPDATE KTS_TEST SET NAME = ?, DESCRIPTION = ? WHERE TEST_SEQ = ?");
                pstmt.setString(1, testName);
                pstmt.setString(2, testDescription);
                pstmt.setInt(3, TEST_SEQ);
                pstmt.executeUpdate();
            }

            //step 2. 관계 생성
            if(rels.stream().count() > 0){
                String values = "";
                for(int listIndex = 0; listIndex < rels.stream().count(); listIndex++){
                    TEST_REL_TESTCASE rel = rels.get(listIndex);
                    values += "(" + rel.getRELATION_SEQ() + ", " + TEST_SEQ + ", " + listIndex + ", '" + rel.getTESTCASE_GUID() + "')";
                    if(listIndex < rels.stream().count()-1){
                        values += ",";
                    }
                }
                pstmt = conn.prepareStatement("INSERT INTO TEST_REL_TESTCASE (RELATION_SEQ, TEST_SEQ, LIST_INDEX, TESTCASE_GUID) VALUES " + values
                        + " ON DUPLICATE KEY UPDATE RELATION_SEQ = RELATION_SEQ, LIST_INDEX = VALUES(LIST_INDEX)");
                pstmt.executeUpdate();
            }

            //step 3. 삭제된 관계 DELETED=Y로 전환
            if(removeRelationSeqList != null && removeRelationSeqList.stream().count() > 0){
                StringBuilder builder = new StringBuilder();
                Iterator<Integer> iter = removeRelationSeqList.iterator();
                while(iter.hasNext()){
                    builder.append(iter.next());
                    if(iter.hasNext()){
                        builder.append(",");
                    }
                }
                pstmt = conn.prepareStatement("UPDATE TEST_REL_TESTCASE SET DELETED = 'Y' WHERE RELATION_SEQ IN (" + builder.toString() + ")");
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
