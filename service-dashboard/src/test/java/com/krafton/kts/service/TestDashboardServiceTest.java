package com.krafton.kts.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestDashboardServiceTest {

    @Autowired
    TestDashboardService testDashboardService;//내부의 인터페이스 빈들이 없어서 에러 발생.

    @Test
    public void findAllRunningTest(){
        System.out.println(testDashboardService.findAllRunningTest());
    }
}
