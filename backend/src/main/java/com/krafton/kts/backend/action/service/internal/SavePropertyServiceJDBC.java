package com.krafton.kts.backend.action.service.internal;

import com.krafton.kts.backend.action.domain.command.SavePropertiesCommand;
import com.krafton.kts.backend.action.domain.db.KTS_PROPERTY;
import com.krafton.kts.backend.common.JdbcCommon;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SavePropertyServiceJDBC extends JdbcCommon implements SavePropertyService {
    public SavePropertyServiceJDBC(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void saveProperties(SavePropertiesCommand command) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);//트랜잭션 처리

            //step 1. 기존 프로퍼티 정보 삭제(DELETED = Y로 변경)
            pstmt = conn.prepareStatement("UPDATE KTS_PROPERTY SET deleted = 'Y' WHERE actionGuid = ?");
            pstmt.setString(1, command.getActionGuid());
            pstmt.executeUpdate();

            //step 2. 프로퍼티 정보 저장
            String values = "";
            for(int i = 0; i < command.getProperties().stream().count(); i++){
                KTS_PROPERTY prop = command.getProperties().get(i);
                values += "(" + prop.getPropertySeq() + ", '" + prop.getPropertyName() + "', '" + prop.getPropertyValue() + "', '" + prop.getActionGuid() + "')";
                if(i < command.getProperties().stream().count()-1){
                    values += ",";
                }
            }
            pstmt = conn.prepareStatement("INSERT INTO KTS_PROPERTY (propertySeq, propertyName, propertyValue, actionGuid) VALUES " + values
                    + " ON DUPLICATE KEY UPDATE propertyName = VALUES(propertyName), propertyValue = VALUES(propertyValue), actionGuid = VALUES(actionGuid)");
            pstmt.executeUpdate();

            //step 3. 액션의 액션ID 변경
            pstmt = conn.prepareStatement("UPDATE KTS_ACTION SET actionId = ? WHERE actionGuid = ?");
            pstmt.setString(1, command.getActionId());
            pstmt.setString(2, command.getActionGuid());
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
