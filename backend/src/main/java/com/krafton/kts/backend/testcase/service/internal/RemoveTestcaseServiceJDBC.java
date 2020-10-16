package com.krafton.kts.backend.testcase.service.internal;

import com.krafton.kts.backend.common.JdbcCommon;
import com.krafton.kts.backend.testcase.domain.command.RemoveTestcaseCommand;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RemoveTestcaseServiceJDBC extends JdbcCommon implements RemoveTestcaseService {
    public RemoveTestcaseServiceJDBC(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void removeTestcase(RemoveTestcaseCommand command) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);//트랜잭션 처리

            //step 1. TEST_REL_TESTCASE 에서 해당 TESTCASE_GUID를 가지는 로우 삭제 처리
            pstmt = conn.prepareStatement("UPDATE TEST_REL_TESTCASE SET deleted = 'Y' WHERE testcaseGuid = ?");
            pstmt.setString(1, command.getTestcaseGuid());
            pstmt.executeUpdate();

            //step 2. KTS_ACTION과 KTS_PROPERTY를 조인하여, 현재 삭제해야 하는 TESTCASE_GUID에 해당하는 ACTION들에 포함된 PROPERTY들의 PROPERTY_SEQ 리스트 확보
            pstmt = conn.prepareStatement("SELECT propertySeq FROM KTS_PROPERTY WHERE actionGuid IN (" +
                    "SELECT actionGuid FROM KTS_ACTION WHERE testcaseGuid = ? AND deleted = 'N' GROUP BY actionGuid)");
            pstmt.setString(1, command.getTestcaseGuid());
            ResultSet rs = pstmt.executeQuery();
            List<Integer> propertySeqList = new ArrayList<>();
            while(rs.next()){
                propertySeqList.add(rs.getInt(1));
            }
            StringBuilder builder = new StringBuilder();
            Iterator<Integer> iter = propertySeqList.iterator();
            while(iter.hasNext()){
                builder.append(iter.next());
                if(iter.hasNext()){
                    builder.append(",");
                }
            }

            //step 3. KTS_PROPERTY에서 위에서 구한 SEQ에 해당하는 PROPERTY들 삭제 처리
            pstmt = conn.prepareStatement("UPDATE KTS_PROPERTY SET deleted = 'Y' WHERE propertySeq IN (" + builder.toString() + ")");
            pstmt.executeUpdate();

            //step 4. KTS_ACTION에서 해당 TESTCASE_GUID를 가지는 로우 삭제 처리
            pstmt = conn.prepareStatement("UPDATE KTS_ACTION SET deleted = 'Y' WHERE testcaseGuid = ?");
            pstmt.setString(1, command.getTestcaseGuid());
            pstmt.executeUpdate();

            //step 5. KTS_TESTCASE에서 해당 테스트케이스 삭제
            pstmt = conn.prepareStatement("UPDATE KTS_TESTCASE SET deleted = 'Y' WHERE testcaseGuid = ?");
            pstmt.setString(1, command.getTestcaseGuid());
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
