package com.krafton.kts.backend.property.repository;

import com.krafton.kts.backend.action.domain.KTS_ACTION;
import com.krafton.kts.backend.common.JdbcCommon;
import com.krafton.kts.backend.property.domain.KTS_PROPERTY;
import com.krafton.kts.backend.property.domain.KTS_PROPERTY_TEMPLETE;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RepoJdbc_property extends JdbcCommon implements Repo_property {

    public RepoJdbc_property(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<KTS_PROPERTY> findProperty(String ACTION_GUID) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select * from KTS_PROPERTY where ACTION_GUID = ? AND DELETED = 'N'");
            pstmt.setString(1, ACTION_GUID);
            rs = pstmt.executeQuery();

            List<KTS_PROPERTY> list = new ArrayList<>();
            while(rs.next()){
                KTS_PROPERTY prop = new KTS_PROPERTY();
                prop.setPROPERTY_SEQ(rs.getInt("PROPERTY_SEQ"));
                prop.setPROPERTY_NAME(rs.getString("PROPERTY_NAME"));
                prop.setPROPERTY_VALUE(rs.getString("PROPERTY_VALUE"));
                prop.setACTION_GUID(rs.getString("ACTION_GUID"));
                prop.setDELETED(rs.getString("DELETED"));
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
    public List<KTS_PROPERTY_TEMPLETE> findPropertyTemplete(String ACTION_ID) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select * from KTS_PROPERTY_TEMPLETE where ACTION_ID = ? ");
            pstmt.setString(1, ACTION_ID);
            rs = pstmt.executeQuery();

            List<KTS_PROPERTY_TEMPLETE> list = new ArrayList<>();
            while(rs.next()){
                KTS_PROPERTY_TEMPLETE prop = new KTS_PROPERTY_TEMPLETE();
                prop.setACTION_ID(rs.getString("ACTION_ID"));
                prop.setPROPERTY_NAME(rs.getString("PROPERTY_NAME"));
                prop.setPROPERTY_VALUE(rs.getString("PROPERTY_VALUE"));
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
    public void saveProperties(List<KTS_PROPERTY> list, String ACTION_GUID, String ACTION_ID) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);//트랜잭션 처리

            //step 1. 기존 프로퍼티 정보 삭제(DELETED = Y로 변경)
            pstmt = conn.prepareStatement("UPDATE KTS_PROPERTY SET DELETED = 'Y' WHERE ACTION_GUID = ?");
            pstmt.setString(1, ACTION_GUID);
            pstmt.executeUpdate();

            //step 2. 프로퍼티 정보 저장
            String values = "";
            for(int i = 0; i < list.stream().count(); i++){
                KTS_PROPERTY prop = list.get(i);
                values += "(" + prop.getPROPERTY_SEQ() + ", '" + prop.getPROPERTY_NAME() + "', '" + prop.getPROPERTY_VALUE() + "', '" + prop.getACTION_GUID() + "')";
                if(i < list.stream().count()-1){
                    values += ",";
                }
            }
            pstmt = conn.prepareStatement("INSERT INTO KTS_PROPERTY (PROPERTY_SEQ, PROPERTY_NAME, PROPERTY_VALUE, ACTION_GUID) VALUES " + values
                    + " ON DUPLICATE KEY UPDATE PROPERTY_NAME = VALUES(PROPERTY_NAME), PROPERTY_VALUE = VALUES(PROPERTY_VALUE), ACTION_GUID = VALUES(ACTION_GUID)");
            pstmt.executeUpdate();

            //step 3. 액션의 액션ID 변경 -> 이거 도메인 규정 위반이다. 근데 어떻게 고치지?
            pstmt = conn.prepareStatement("UPDATE KTS_ACTION SET ACTION_ID = ? WHERE ACTION_GUID = ?");
            pstmt.setString(1, ACTION_ID);
            pstmt.setString(2, ACTION_GUID);
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
