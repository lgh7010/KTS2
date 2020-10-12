package com.krafton.kts.backend.test_rel_testcase.repository;

import com.krafton.kts.backend.common.JdbcCommon;
import com.krafton.kts.backend.test_rel_testcase.domain.TEST_REL_TESTCASE;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
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
            pstmt = conn.prepareStatement("select * from TEST_REL_TESTCASE where TEST_SEQ = ? and DELETED = 'N'");
            pstmt.setInt(1, TEST_SEQ);
            rs = pstmt.executeQuery();

            List<TEST_REL_TESTCASE> rels = new ArrayList<>();
            while(rs.next()){
                TEST_REL_TESTCASE rel = new TEST_REL_TESTCASE();
                rel.setRELATION_SEQ(rs.getInt("RELATION_SEQ"));
                rel.setTEST_SEQ(rs.getInt("TEST_SEQ"));
                rel.setLIST_INDEX(rs.getInt("LIST_INDEX"));
                rel.setTESTCASE_SEQ(rs.getInt("TESTCASE_SEQ"));
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
    public List<TEST_REL_TESTCASE> findTestRelTestcaseByTESTCASE_SEQ(int TESTCASE_SEQ) {
        return null;
    }

    @Override
    public void saveTestRelTestcase(List<TEST_REL_TESTCASE> rels, int TEST_SEQ, Boolean createNewTest) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);//트랜잭션 처리

            //step 1. 테스트 생성
            if(createNewTest){
                pstmt = conn.prepareStatement("INSERT INTO KTS_TEST (NAME, DESCRIPTION) VALUES ('기본값', '기본설명')", Statement.RETURN_GENERATED_KEYS);
                pstmt.executeUpdate();

                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        TEST_SEQ = generatedKeys.getInt(1);
                    }
                    else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
            }

            String values = "";
            for(int i = 0; i < rels.stream().count(); i++){
                TEST_REL_TESTCASE rel = rels.get(i);
                values += "(" + rel.getRELATION_SEQ() + ", " + TEST_SEQ + ", " + rel.getLIST_INDEX() + ", " + rel.getTESTCASE_SEQ() + ")";
                if(i < rels.stream().count()-1){
                    values += ",";
                }
            }

            //step 2. 관계 생성
            pstmt = conn.prepareStatement("INSERT INTO TEST_REL_TESTCASE (RELATION_SEQ, TEST_SEQ, LIST_INDEX, TESTCASE_SEQ) VALUES " + values
            + " ON DUPLICATE KEY UPDATE RELATION_SEQ = RELATION_SEQ");
            pstmt.executeUpdate();

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
