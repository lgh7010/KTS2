package com.krafton.kts.backend.action.repository;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.krafton.kts.backend.action.domain.KTS_ACTION;
import com.krafton.kts.backend.action.domain.KTS_ACTION_TEMPLETE;
import com.krafton.kts.backend.common.JdbcCommon;
import com.krafton.kts.backend.test_rel_testcase.domain.TEST_REL_TESTCASE;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RepoJdbc_action extends JdbcCommon implements Repo_action {
    public RepoJdbc_action(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<KTS_ACTION> findActionsByTESTCASE_GUID(String TESTCASE_GUID) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select * from KTS_ACTION where TESTCASE_GUID = ? AND DELETED = 'N'");
            pstmt.setString(1, TESTCASE_GUID);
            rs = pstmt.executeQuery();

            List<KTS_ACTION> list = new ArrayList<>();
            while(rs.next()){
                KTS_ACTION action = new KTS_ACTION();
                action.setACTION_GUID(rs.getString("ACTION_GUID"));
                action.setTESTCASE_GUID(rs.getString("TESTCASE_GUID"));
                action.setIS_START(rs.getString("IS_START"));
                action.setNEXT_ACTION_GUID(rs.getString("NEXT_ACTION_GUID"));
                action.setACTION_ID(rs.getString("ACTION_ID"));
                action.setX_POS(rs.getFloat("X_POS"));
                action.setY_POS(rs.getFloat("Y_POS"));
                action.setDESCRIPTION(rs.getString("DESCRIPTION"));
                action.setDELETED(rs.getString("DELETED"));
                list.add(action);
            }
            return list;
        } catch (Exception e){
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<KTS_ACTION_TEMPLETE> findAllTemplete() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select * from KTS_ACTION_TEMPLETE");
            rs = pstmt.executeQuery();

            List<KTS_ACTION_TEMPLETE> list = new ArrayList<>();
            while(rs.next()){
                KTS_ACTION_TEMPLETE tp = new KTS_ACTION_TEMPLETE();
                tp.setACTION_ID(rs.getString("ACTION_ID"));
                tp.setTYPE(rs.getString("TYPE"));
                tp.setTEMPLETE_DESCRIPTION(rs.getString("TEMPLETE_DESCRIPTION"));
                list.add(tp);
            }
            return list;
        } catch (Exception e){
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public void saveActionList(List<KTS_ACTION> list, List<String> removeList) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);//트랜잭션 처리

            //step 1. list의 내용 저장
            String values = "";
            for(int i = 0; i < list.stream().count(); i++){
                KTS_ACTION action = list.get(i);
                values += "('" + action.getACTION_GUID() + "', '" + action.getTESTCASE_GUID() + "', '" + action.getIS_START() + "', '" + action.getNEXT_ACTION_GUID() + "', '" + action.getACTION_ID() + "', " + action.getX_POS() + ", " + action.getY_POS() + ", '" + action.getDESCRIPTION() + "')";
                if(i < list.stream().count()-1){
                    values += ",";
                }
            }
            pstmt = conn.prepareStatement("INSERT INTO KTS_ACTION (ACTION_GUID, TESTCASE_GUID, IS_START, NEXT_ACTION_GUID, ACTION_ID, X_POS, Y_POS, DESCRIPTION) VALUES " + values
                    + " ON DUPLICATE KEY UPDATE TESTCASE_GUID = VALUES(TESTCASE_GUID), IS_START = VALUES(IS_START), NEXT_ACTION_GUID = VALUES(NEXT_ACTION_GUID), ACTION_ID = VALUES(ACTION_ID), X_POS = VALUES(X_POS), Y_POS = VALUES(Y_POS), DESCRIPTION = VALUES(DESCRIPTION)");
            pstmt.executeUpdate();

            //step 2. removeList에 SEQ가 존재하는 ACTION들의 DELETED를 Y로 변경
            if(removeList != null && removeList.stream().count() > 0){
                StringBuilder builder = new StringBuilder();
                Iterator<String> iter = removeList.iterator();
                while(iter.hasNext()){
                    builder.append("'" + iter.next() + "'");
                    if(iter.hasNext()){
                        builder.append(",");
                    }
                }
                pstmt = conn.prepareStatement("UPDATE KTS_ACTION SET DELETED = 'Y' WHERE ACTION_GUID IN (" + builder.toString() + ")");
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
