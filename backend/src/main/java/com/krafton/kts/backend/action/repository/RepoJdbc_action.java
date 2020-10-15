package com.krafton.kts.backend.action.repository;

import com.krafton.kts.backend.action.domain.KTS_ACTION;
import com.krafton.kts.backend.action.domain.KTS_ACTION_TEMPLETE;
import com.krafton.kts.backend.common.JdbcCommon;

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
    public List<KTS_ACTION> findActionsByTESTCASE_GUID(String testcaseGuid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select * from KTS_ACTION where testcaseGuid = ? AND deleted = 'N'");
            pstmt.setString(1, testcaseGuid);
            rs = pstmt.executeQuery();

            List<KTS_ACTION> list = new ArrayList<>();
            while(rs.next()){
                KTS_ACTION action = new KTS_ACTION();
                action.setActionGuid(rs.getString("actionGuid"));
                action.setTestcaseGuid(rs.getString("testcaseGuid"));
                action.setIsStart(rs.getString("isStart"));
                action.setNextActionGuid(rs.getString("nextActionGuid"));
                action.setActionId(rs.getString("actionId"));
                action.setX(rs.getFloat("x"));
                action.setY(rs.getFloat("y"));
                action.setDescription(rs.getString("description"));
                action.setDeleted(rs.getString("deleted"));
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
            pstmt = conn.prepareStatement("SELECT * FROM KTS_ACTION_TEMPLETE");
            rs = pstmt.executeQuery();

            List<KTS_ACTION_TEMPLETE> list = new ArrayList<>();
            while(rs.next()){
                KTS_ACTION_TEMPLETE tp = new KTS_ACTION_TEMPLETE();
                tp.setActionId(rs.getString("actionId"));
                tp.setType(rs.getString("type"));
                tp.setTempleteDescription(rs.getString("templeteDescription"));
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
                values += "('" + action.getActionGuid() + "', '" + action.getTestcaseGuid() + "', '" + action.getIsStart() + "', '" + action.getNextActionGuid() + "', '" + action.getActionId() + "', " + action.getX() + ", " + action.getY() + ", '" + action.getDescription() + "')";
                if(i < list.stream().count()-1){
                    values += ",";
                }
            }
            pstmt = conn.prepareStatement("INSERT INTO KTS_ACTION (actionGuid, testcaseGuid, isStart, nextActionGuid, actionId, x, y, description) VALUES " + values
                    + " ON DUPLICATE KEY UPDATE testcaseGuid = VALUES(testcaseGuid), isStart = VALUES(isStart), nextActionGuid = VALUES(nextActionGuid), actionId = VALUES(actionId), x = VALUES(x), y = VALUES(y), description = VALUES(description)");
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
                pstmt = conn.prepareStatement("UPDATE KTS_ACTION SET deleted = 'Y' WHERE actionGuid IN (" + builder.toString() + ")");
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
