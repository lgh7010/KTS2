package com.krafton.kts.backend.action.service.internal;

import com.krafton.kts.backend.action.domain.db.KTS_ACTION;
import com.krafton.kts.backend.common.JdbcCommon;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FindActionServiceJDBC extends JdbcCommon implements FindActionService {
    public FindActionServiceJDBC(DataSource dataSource) {
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
}
