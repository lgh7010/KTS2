//package com.krafton.kts.service;
//
//import com.krafton.kts.domains.derived.TEST_REL_TESTCASE_JOIN_TESTCASE;
//import com.krafton.kts.domains.entity.KTS_TEST;
//import com.krafton.kts.domains.entity.KTS_TESTCASE;
//import com.krafton.kts.interfaces.repository.derived.DerivedDomainInterface;
//import com.krafton.kts.interfaces.repository.test.TestInterface;
//import com.krafton.kts.interfaces.repository.testcase.TestcaseInterface;
//import com.krafton.kts.interfaces.repository.testreltestcase.TestRelTestcaseInterface;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.BDDMockito.given;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest(classes = TestEditComponentService.class)
//public class TestEditComponentServiceTest {
//
//    @Autowired
//    private TestEditComponentService testEditComponentService;
//
//    @MockBean
//    private TestInterface testInterface;
//    @MockBean
//    private DerivedDomainInterface derivedDomainInterface;
//    @MockBean
//    private TestcaseInterface testcaseInterface;
//    @MockBean
//    private TestRelTestcaseInterface testRelTestcaseInterface;
//
//    @Test
//    public void findTest(){
//        //given
//        KTS_TEST test = new KTS_TEST();
//        test.setName("test01");
//        test.setDeleted("this is test 01");
//        test.setTestGuid("GUID1");
//        test.setDeleted("N");
//        given(testInterface.findTest("GUID1")).willReturn(test);
//
//        //when
//        KTS_TEST ret = testEditComponentService.findTest("GUID1");
//
//        //then
//        assertEquals(test.getName(), ret.getName());
//        assertEquals(test.getDescription(), ret.getDescription());
//        assertEquals(test.getDeleted(), ret.getDeleted());
//        assertEquals(test.getTestGuid(), ret.getTestGuid());
//    }
//
//    @Test
//    public void findTestRelTestcaseJoinTestcase(){
//        //given
//        List<TEST_REL_TESTCASE_JOIN_TESTCASE> list = new ArrayList<>();
//        TEST_REL_TESTCASE_JOIN_TESTCASE elem = new TEST_REL_TESTCASE_JOIN_TESTCASE();
//        elem.setName("name1");
//        given(this.derivedDomainInterface.findTestRelTestcaseJoinTestcase("GUID1")).willReturn(list);
//
//        //when
//        List<TEST_REL_TESTCASE_JOIN_TESTCASE> ret = this.testEditComponentService.findTestRelTestcaseJoinTestcase("GUID1");
//
//        //then
//        assertEquals(list.stream().count(), ret.stream().count());
//        for(int i = 0; i < list.stream().count(); i++){
//            assertEquals(list.get(i).getName(), ret.get(i).getName());
//        }
//    }
//
//    @Test
//    public void findAllTestcase(){
//        //given
//        List<KTS_TESTCASE> list = new ArrayList<>();
//        KTS_TESTCASE testcase = new KTS_TESTCASE();
//        testcase.setName("name1");
//        given(this.testcaseInterface.findAll()).willReturn(list);
//
//        //when
//        List<KTS_TESTCASE> ret = this.testEditComponentService.findAllTestcase();
//
//        //then
//        assertEquals(list.stream().count(), ret.stream().count());
//        for(int i = 0; i < list.stream().count(); i++){
//            assertEquals(list.get(i).getName(), ret.get(i).getName());
//        }
//    }
//    @Test
//    public void saveTestRelTestcase() {
//        //interface Bean은 spyBean을 지원하지 않는다고 한다. https://jojoldu.tistory.com/226
//        //따라서 저장 기능은 Repo단위에서만 확인하고, 각 서비스의 유닛테스트에서는 확인하지 않는다.
//        //근데 저장기능은 현재 mybatis만 사용하고 있어서, 이건 다 인터페이스이므로 따로 Mock테스트를 할 방법이 없는 상태.
//    }
//}
