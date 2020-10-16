package com.krafton.kts.backend.action.service.internal;

import com.krafton.kts.backend.action.domain.db.KTS_ACTION_TEMPLATE;
import com.krafton.kts.backend.common.JdbcCommon;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class GetActionTemplateServiceJDBC extends JdbcCommon implements GetActionTemplateService {
    public GetActionTemplateServiceJDBC(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Map<String, KTS_ACTION_TEMPLATE> getActionTemplate() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM KTS_ACTION_TEMPLATE");
            rs = pstmt.executeQuery();

            Map<String, KTS_ACTION_TEMPLATE> ret = new HashMap<>();
            while(rs.next()){
                KTS_ACTION_TEMPLATE tp = new KTS_ACTION_TEMPLATE();
                tp.setActionId(rs.getString("actionId"));
                tp.setType(rs.getString("type"));
                tp.setTemplateDescription(rs.getString("templateDescription"));

                ret.put(rs.getString("actionId"), tp);
            }
            return ret;
        } catch (Exception e){
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
}
