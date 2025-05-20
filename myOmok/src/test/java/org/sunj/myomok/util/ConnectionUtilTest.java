package org.sunj.myomok.util;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
여기선 ConnectionUtil을 테스트하기 위한 클래스이다.
 */

@Log4j2
class ConnectionUtilTest {
    @Test
    @DisplayName("[DB TEST] ConnectionUtil의 getConnection() 메서드 정상 작동하는지 확인")
    public void testGetConnection() throws Exception{
        //일단 connection utils를 가져와야 한다.
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        //아무거나 sql을 날려본다.
        String sql = "SELECT COUNT(*) AS cnt FROM user";

        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        @Cleanup ResultSet rs = pstmt.executeQuery();

        //tdd는 다음과 같이 작성한다.

        //1. assertiond으로 존재하는지 여부 확인
        Assertions.assertTrue(rs.next());
        //2. 현재 데이터가 없으므로, cnt 0이 맞는지 확인
        Assertions.assertEquals(0, rs.getInt("cnt"));
    }
}