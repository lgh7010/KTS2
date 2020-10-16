package com.krafton.kts.backend.domain.action.service.interfaces;

import com.krafton.kts.backend.domain.action.domain.command.SavePropertiesCommand;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION_PROPERTY;
import com.krafton.kts.backend.domain.action.domain.db.KTS_ACTION_PROPERTY_TEMPLATE;
import com.krafton.kts.backend.common.JdbcCommon;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PropertyInterfaceJDBC extends JdbcCommon implements PropertyInterface {
    public PropertyInterfaceJDBC(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<KTS_ACTION_PROPERTY> findProperty(String actionGuid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select * from KTS_ACTION_PROPERTY where actionGuid = ? AND deleted = 'N'");
            pstmt.setString(1, actionGuid);
            rs = pstmt.executeQuery();

            List<KTS_ACTION_PROPERTY> list = new ArrayList<>();
            while(rs.next()){
                KTS_ACTION_PROPERTY prop = new KTS_ACTION_PROPERTY();
                prop.setPropertySeq(rs.getInt("propertySeq"));
                prop.setPropertyName(rs.getString("propertyName"));
                prop.setPropertyValue(rs.getString("propertyValue"));
                prop.setActionGuid(rs.getString("actionGuid"));
                prop.setDeleted(rs.getString("deleted"));
                list.add(prop);
            }
            return list;
        } catch (Exception e){
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<KTS_ACTION_PROPERTY_TEMPLATE> getPropertyTemplate(String actionId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select * from KTS_ACTION_PROPERTY_TEMPLATE where actionId = ? ");
            pstmt.setString(1, actionId);
            rs = pstmt.executeQuery();

            List<KTS_ACTION_PROPERTY_TEMPLATE> list = new ArrayList<>();
            while(rs.next()){
                KTS_ACTION_PROPERTY_TEMPLATE prop = new KTS_ACTION_PROPERTY_TEMPLATE();
                prop.setActionId(rs.getString("actionId"));
                prop.setPropertyName(rs.getString("propertyName"));
                prop.setPropertyValue(rs.getString("propertyValue"));
                list.add(prop);
            }
            return list;
        } catch (Exception e){
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public void saveProperties(SavePropertiesCommand command) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);//트랜잭션 처리

            //step 1. 기존 프로퍼티 정보 삭제(DELETED = Y로 변경)
            pstmt = conn.prepareStatement("UPDATE KTS_ACTION_PROPERTY SET deleted = 'Y' WHERE actionGuid = ?");
            pstmt.setString(1, command.getActionGuid());
            pstmt.executeUpdate();

            //step 2. 프로퍼티 정보 저장
            String values = "";
            for(int i = 0; i < command.getProperties().stream().count(); i++){
                KTS_ACTION_PROPERTY prop = command.getProperties().get(i);
                values += "(" + prop.getPropertySeq() + ", '" + prop.getPropertyName() + "', '" + prop.getPropertyValue() + "', '" + prop.getActionGuid() + "')";
                if(i < command.getProperties().stream().count()-1){
                    values += ",";
                }
            }
            pstmt = conn.prepareStatement("INSERT INTO KTS_ACTION_PROPERTY (propertySeq, propertyName, propertyValue, actionGuid) VALUES " + values
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
