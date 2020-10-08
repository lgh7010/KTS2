package com.krafton.kts.backend.action.repository;

import com.krafton.kts.backend.action.domain.KTS_ACTION;
import com.krafton.kts.backend.common.JdbcCommon;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RepoJdbc_action extends JdbcCommon implements Repo_action {
    public RepoJdbc_action(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<KTS_ACTION> findActionsByTESTCASE_SEQ(int TESTCASE_SEQ) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select * from KTS_ACTION where TESTCASE_SEQ = ?");
            pstmt.setInt(1, TESTCASE_SEQ);
            rs = pstmt.executeQuery();

            List<KTS_ACTION> list = new ArrayList<>();
            while(rs.next()){
                KTS_ACTION action = new KTS_ACTION();
                action.setACTION_SEQ(rs.getInt("ACTION_SEQ"));
                action.setTESTCASE_SEQ(rs.getInt("TESTCASE_SEQ"));
                action.setIS_START(rs.getString("IS_START"));
                action.setNEXT_ACTION_SEQ(rs.getInt("NEXT_ACTION_SEQ"));
                action.setACTION_ID(rs.getString("ACTION_ID"));
                action.setX_POS(rs.getFloat("X_POS"));
                action.setY_POS(rs.getFloat("Y_POS"));
                action.setDESCRIPTION(rs.getString("DESCRIPTION"));
                action.setPROPERTY_JSON(rs.getString("PROPERTY_JSON"));
                action.setDELETED(rs.getString("DELETED"));
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
