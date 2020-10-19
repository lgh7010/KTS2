package com.krafton.kts.backend.domain.action.service.impl.jdbc;

import com.krafton.kts.backend.domain.action.domain.command.SaveActionCommand;
import com.krafton.kts.backend.domain.action.domain.command.UpdateActionIdCommand;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION_PROPERTY;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION_TEMPLATE;
import com.krafton.kts.backend.common.JdbcCommon;
import com.krafton.kts.backend.domain.action.service.impl.ActionInterface;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ActionInterfaceJDBC extends JdbcCommon implements ActionInterface {
    public ActionInterfaceJDBC(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<KTS_ACTION> findAction(String testcaseGuid) {
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
    public List<KTS_ACTION_TEMPLATE> getActionTemplate() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM KTS_ACTION_TEMPLATE");
            rs = pstmt.executeQuery();

            List<KTS_ACTION_TEMPLATE> ret = new ArrayList<>();
            while(rs.next()){
                KTS_ACTION_TEMPLATE tp = new KTS_ACTION_TEMPLATE();
                tp.setActionId(rs.getString("actionId"));
                tp.setType(rs.getString("type"));
                tp.setTemplateDescription(rs.getString("templateDescription"));

                ret.add(tp);
            }
            return ret;
        } catch (Exception e){
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public void saveAction(SaveActionCommand command) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);//트랜잭션 처리

            //step 1. list의 내용 저장
            String values = "";
            Iterator<Map.Entry<String, KTS_ACTION>> mapIterator = command.getActionMap().entrySet().iterator();
            while(mapIterator.hasNext()){
                KTS_ACTION action = mapIterator.next().getValue();
                values += "('" + action.getActionGuid() + "', '" + action.getTestcaseGuid() + "', '" + action.getIsStart() + "', '" + action.getNextActionGuid() + "', '" + action.getActionId() + "', " + action.getX() + ", " + action.getY() + ", '" + action.getDescription() + "')";
                if(mapIterator.hasNext()){
                    values += ",";
                }
            }
            pstmt = conn.prepareStatement("INSERT INTO KTS_ACTION (actionGuid, testcaseGuid, isStart, nextActionGuid, actionId, x, y, description) VALUES " + values
                    + " ON DUPLICATE KEY UPDATE testcaseGuid = VALUES(testcaseGuid), isStart = VALUES(isStart), nextActionGuid = VALUES(nextActionGuid), actionId = VALUES(actionId), x = VALUES(x), y = VALUES(y), description = VALUES(description)");
            pstmt.executeUpdate();

            //step 2. removeList에 SEQ가 존재하는 ACTION들의 DELETED를 Y로 변경
            if(command.getRemoveList() != null && command.getRemoveList().stream().count() > 0){
                StringBuilder builder = new StringBuilder();
                Iterator<String> iter = command.getRemoveList().iterator();
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

    @Override
    public void updateActionId(UpdateActionIdCommand command) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            pstmt = conn.prepareStatement("UPDATE KTS_ACTION SET actionId = ? WHERE actionGuid = ?");
            pstmt.setString(1, command.getActionId());
            pstmt.setString(2, command.getActionGuid());
            pstmt.executeUpdate();
        } catch (Exception e){
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt);
        }
    }
}
