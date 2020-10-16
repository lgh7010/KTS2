package com.krafton.kts.backend.action.service.internal;

import com.krafton.kts.backend.action.domain.db.KTS_PROPERTY;
import com.krafton.kts.backend.common.JdbcCommon;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FindPropertyServiceJDBC extends JdbcCommon implements FindPropertyService {
    public FindPropertyServiceJDBC(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<KTS_PROPERTY> findProperty(String actionGuid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select * from KTS_PROPERTY where actionGuid = ? AND deleted = 'N'");
            pstmt.setString(1, actionGuid);
            rs = pstmt.executeQuery();

            List<KTS_PROPERTY> list = new ArrayList<>();
            while(rs.next()){
                KTS_PROPERTY prop = new KTS_PROPERTY();
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
}
