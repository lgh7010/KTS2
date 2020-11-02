package com.krafton.kts.service;

import com.krafton.kts.domains.entity.KtsAction;
import com.krafton.kts.domains.entity.KtsActionTemplate;
import com.krafton.kts.interfaces.repository.action.ActionInterface;
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

public class FindActionServiceTest {

    private FindActionService findActionService;

    @Mock
    private ActionInterface actionInterface;

    @BeforeEach
    public void beforeEach(){
        this.actionInterface = mock(ActionInterface.class);
        this.findActionService = new FindActionService(this.actionInterface);
    }

    @Test
    public void getActionTemplate() {
        //given
        List<KtsActionTemplate> list = new ArrayList<>();
        KtsActionTemplate template = new KtsActionTemplate();
        template.setActionId("actionId1");
        list.add(template);
        given(this.actionInterface.getActionTemplate()).willReturn(list);

        //when
        Map<String, KtsActionTemplate> res = this.findActionService.getActionTemplate();

        //then
        assertTrue(res.containsKey("actionId1"));
        assertEquals(res.get("actionId1").getActionId(), list.get(0).getActionId());
    }

    @Test
    public void findAction() {
        //given

        //when

        //then

    }
}
