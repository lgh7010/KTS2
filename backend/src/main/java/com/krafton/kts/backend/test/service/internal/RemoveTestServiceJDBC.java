package com.krafton.kts.backend.test.service.internal;

import com.krafton.kts.backend.common.JdbcCommon;
import com.krafton.kts.backend.test.domain.command.RemoveTestCommand;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveTestServiceJDBC extends JdbcCommon implements RemoveTestService {

    public RemoveTestServiceJDBC(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void RemoveTest(RemoveTestCommand command) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);//트랜잭션 처리

            //step 1. KTS_TEST 테이블에서 해당 테스트의 DELETED = Y로 변경
            pstmt = conn.prepareStatement("update KTS_TEST set deleted = 'Y' where testSeq = ?");
            pstmt.setInt(1, command.getTestSeq());
            pstmt.executeUpdate();

            //step 2. TEST_REL_TESTCASE 테이블에서 해당하는 TEST_SEQ의 모든 관계내역의 DELETED = Y로 변경
            pstmt = conn.prepareStatement("update TEST_REL_TESTCASE set deleted = 'Y' where testSeq = ?");
            pstmt.setInt(1, command.getTestSeq());
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
