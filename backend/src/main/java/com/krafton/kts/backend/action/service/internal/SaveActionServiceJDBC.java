package com.krafton.kts.backend.action.service.internal;

import com.krafton.kts.backend.action.domain.db.KTS_ACTION;
import com.krafton.kts.backend.common.JdbcCommon;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SaveActionServiceJDBC extends JdbcCommon implements SaveActionService {
    public SaveActionServiceJDBC(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void saveAction(Map<String, KTS_ACTION> map, List<String> removeList) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);//트랜잭션 처리

            //step 1. list의 내용 저장
            String values = "";
            Iterator<Map.Entry<String, KTS_ACTION>> mapIterator = map.entrySet().iterator();
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
