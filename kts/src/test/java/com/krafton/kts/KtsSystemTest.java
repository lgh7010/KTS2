//package com.krafton.kts;
//
//import com.krafton.kts.service.*;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class KtsSystemTest {
//
//    @Autowired
//    TestDashboardService testDashboardService;
//    @Autowired
//    RemoveTestService testListComponentService;
//    @Autowired
//    SaveTestRelTestcaseService testEditComponentService;
//    @Autowired
//    RemoveTestcaseService testcaseListComponentService;
//    @Autowired
//    SaveTestcaseService testcaseEditComponentService;
//
//    @Test
//    public void 테스트_목록_확인(){
//        System.out.println(testListComponentService.findAllTest());
//    }
//
//    @Test
//    public void 테스트케이스_목록_확인(){
//        System.out.println(testcaseListComponentService.findAllTestcase());
//    }
//
//    //전체 테스트는 여기서 이런식으로 짜면 된다.
//    //그런데 각각의 서비스 모듈의 테스트를 짜고 싶다면?
//    //예를 들어 TestDashboardService의 테스트를 짤 때, TestDashboardService만 Autowired로 주입받으면
//    //그 안에서 쓰는 인터페이스들의 빈이 없어서 에러가 난다.
//    //그러면, 테스트 구성클래스를 통해 이들을 모두 명시해 줘야 하나?
//    //아닐거다. MOCK테스트가 이런데 쓰이게끔 되어있을거 같은데..
//}
