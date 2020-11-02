package com.krafton.kts.service;

import com.krafton.kts.domains.entity.KtsTest;
import com.krafton.kts.interfaces.repository.test.TestInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class FindTestServiceTest {

    private FindTestService findTestService;

    @Mock
    private TestInterface testInterface;

    @BeforeEach
    public void beforeEach(){
        this.testInterface = mock(TestInterface.class);
        this.findTestService = new FindTestService(this.testInterface);
    }

    @Test
    public void findTest() {
        //given
        KtsTest test = new KtsTest();
        test.setTestGuid("GUID1");
        given(this.testInterface.findTest("GUID")).willReturn(test);

        //when
        KtsTest res = this.findTestService.findTest("GUID1");

        //then
        assertEquals(test.getTestGuid(), res.getTestGuid());
    }

    @Test
    public void findAllTest() {
        //given
        List<KtsTest> list = new ArrayList<>();
        KtsTest test = new KtsTest();
        test.setTestGuid("GUID1");
        list.add(test);
        given(this.testInterface.findAllTest()).willReturn(list);

        //when
        List<KtsTest> res = this.findTestService.findAllTest();

        //then
        assertEquals(list.stream().count(), res.stream().count());
        for(int i = 0; i < list.stream().count(); i++){
            assertEquals(list.get(i).getTestGuid(), res.get(i).getTestGuid());
        }
    }
}
