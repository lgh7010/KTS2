package com.krafton.kts.backend.action.service.internal;

import com.krafton.kts.backend.action.domain.db.KTS_PROPERTY_TEMPLATE;
import com.krafton.kts.backend.common.JdbcCommon;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GetPropertyTemplateServiceJDBC extends JdbcCommon implements GetPropertyTemplateService {
    public GetPropertyTemplateServiceJDBC(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<KTS_PROPERTY_TEMPLATE> getPropertyTemplate(String actionId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select * from KTS_PROPERTY_TEMPLATE where actionId = ? ");
            pstmt.setString(1, actionId);
            rs = pstmt.executeQuery();

            List<KTS_PROPERTY_TEMPLATE> list = new ArrayList<>();
            while(rs.next()){
                KTS_PROPERTY_TEMPLATE prop = new KTS_PROPERTY_TEMPLATE();
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
}
