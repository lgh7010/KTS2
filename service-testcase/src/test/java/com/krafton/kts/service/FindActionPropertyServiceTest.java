package com.krafton.kts.service;

import com.krafton.kts.domains.entity.KtsAction;
import com.krafton.kts.domains.entity.KtsActionProperty;
import com.krafton.kts.interfaces.repository.action.ActionInterface;
import com.krafton.kts.interfaces.repository.property.FindPropertiesCommand;
import com.krafton.kts.interfaces.repository.property.PropertyInterface;
import com.krafton.kts.interfaces.repository.test.TestInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindActionPropertyServiceTest {
    private FindActionPropertyService findActionPropertyService;

    @Mock
    private ActionInterface actionInterface;
    @Mock
    private PropertyInterface propertyInterface;

    @BeforeEach
    public void beforeEach(){
        this.actionInterface = mock(ActionInterface.class);
        this.propertyInterface = mock(PropertyInterface.class);
        this.findActionPropertyService = new FindActionPropertyService(this.actionInterface, this.propertyInterface);
    }

    @Test
    public void findProperties() {
        //given
        List<KtsAction> actions = new ArrayList<>();
        KtsAction action = new KtsAction();
        action.setActionGuid("actionGuid1");
        actions.add(action);
        given(this.actionInterface.findAction("testcaseGuid1")).willReturn(actions);

        List<KtsActionProperty> list = new ArrayList<>();
        KtsActionProperty property = new KtsActionProperty();
        property.setActionGuid("actionGuid1");
        property.setPropertyName("prop");
        property.setPropertyValue("val");
        list.add(property);
        List<String> actionGuids = new ArrayList<>();
        actionGuids.add("actionGuid1");
        //given(this.propertyInterface.findProperties(new FindPropertiesCommand(actionGuids))).willReturn(list);
        when(this.propertyInterface.findProperties(new FindPropertiesCommand(actionGuids))).thenReturn(list);
        //이렇게 객체를 넘겨받는 메소드 호출에 대해 thenReturn/willReturn조건을 걸면, 해당 객체의 단순 주소를 비교하는게 아니라 내부 값도 비교하는듯 하다.
        //actionGuids에 들어있는 "actionGuid1"을 다른 값으로 고치면, res에 정상적인 맵이 반환되지 않는다.

        //when
        Map<String, List<KtsActionProperty>> res = this.findActionPropertyService.findProperties("testcaseGuid1");

        //then
        assertTrue(res.containsKey("actionGuid1"));
        assertEquals(res.get("actionGuid1").get(0).getPropertyName(), "prop");
        assertEquals(res.get("actionGuid1").get(0).getPropertyValue(), "val");
    }
}
