package com.krafton.kts.service;

import com.krafton.kts.domains.entity.KtsActionProperty;
import com.krafton.kts.domains.entity.KtsActionPropertyTemplate;
import com.krafton.kts.interfaces.repository.property.PropertyInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class FindPropertyServiceTest {

    private FindPropertyService findPropertyService;

    @Mock
    private PropertyInterface propertyInterface;

    @BeforeEach
    public void beforeEach(){
        this.propertyInterface = mock(PropertyInterface.class);
        this.findPropertyService = new FindPropertyService(this.propertyInterface);
    }

    @Test
    public void getPropertyTemplate() {
        //given
        List<KtsActionPropertyTemplate> list = new ArrayList<>();
        KtsActionPropertyTemplate template = new KtsActionPropertyTemplate();
        template.setActionId("actionId1");
        list.add(template);
        given(this.propertyInterface.getPropertyTemplate("actionId1")).willReturn(list);

        //when
        List<KtsActionPropertyTemplate> res = this.findPropertyService.getPropertyTemplate("actionId1");

        //then
        assertEquals(list.stream().count(), res.stream().count());
        for(int i = 0; i < list.stream().count(); i++){
            assertEquals(list.get(i).getActionId(), res.get(i).getActionId());
        }
    }
}
