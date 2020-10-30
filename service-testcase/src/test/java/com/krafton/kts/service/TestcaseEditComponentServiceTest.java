//package com.krafton.kts.service;
//
//import com.krafton.kts.domains.entity.*;
//import com.krafton.kts.interfaces.repository.action.ActionInterface;
//import com.krafton.kts.interfaces.repository.property.PropertyInterface;
//import com.krafton.kts.interfaces.repository.testcase.TestcaseInterface;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.mockito.BDDMockito.given;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class TestcaseEditComponentServiceTest {
//
//    @MockBean
//    private SaveTestcaseService testcaseEditComponentService;
//
//    @MockBean
//    private ActionInterface actionInterface;
//    @MockBean
//    private TestcaseInterface testcaseInterface;
//    @MockBean
//    private PropertyInterface propertyInterface;
//
//    @Test
//    public void getActionTemplate() {
//        //given
//        List<KTS_ACTION_TEMPLATE> list = new ArrayList<>();
//        KTS_ACTION_TEMPLATE template = new KTS_ACTION_TEMPLATE();
//        template.setActionId("action1");
//        list.add(template);
//        given(this.actionInterface.getActionTemplate()).willReturn(list);
//        List<KTS_ACTION_TEMPLATE> resultList = this.actionInterface.getActionTemplate();
//        Map<String, KTS_ACTION_TEMPLATE> ret = new HashMap<>();
//        for (KTS_ACTION_TEMPLATE ktsActionTemplate : resultList) {
//            ret.put(ktsActionTemplate.getActionId(), ktsActionTemplate);
//        }
//
//        //when
//        Map<String, KTS_ACTION_TEMPLATE> map = this.testcaseEditComponentService.getActionTemplate();
//
//        //then
//        assertTrue(map.containsKey("action1"));
//        assertTrue(ret.containsKey("action1"));
//        assertEquals(map.get("action1").getActionId(), ret.get("action1").getActionId());
//    }
//
//    @Test
//    public void findAction() {
//        //given
//        List<KTS_ACTION> list = new ArrayList<>();
//        KTS_ACTION action = new KTS_ACTION();
//        action.setActionGuid("GUID1");
//        list.add(action);
//        given(this.actionInterface.findAction("GUID1")).willReturn(list);
//
//        //when
//        List<KTS_ACTION> ret = this.testcaseEditComponentService.findAction("GUID1");
//
//        //then
//        assertEquals(list.stream().count(), ret.stream().count());
//        for(int i = 0; i < list.stream().count(); i++){
//            assertEquals(list.get(i).getActionGuid(), ret.get(i).getActionGuid());
//        }
//    }
//
//    @Test
//    public void findTestcase() {
//        //given
//        KTS_TESTCASE testcase = new KTS_TESTCASE();
//        testcase.setTestcaseGuid("GUID1");
//        given(this.testcaseInterface.findTestcase("GUID1")).willReturn(testcase);
//
//        //when
//        KTS_TESTCASE ret = this.testcaseEditComponentService.findTestcase("GUID1");
//
//        //then
//        assertEquals(ret.getTestcaseGuid(), testcase.getTestcaseGuid());
//    }
//
//    @Test
//    public void findProperties() {
//
//    }
//
//    @Test
//    public void saveTestcase() {}
//
//    @Test
//    public void getPropertyTemplate() {
//
//    }
//}
