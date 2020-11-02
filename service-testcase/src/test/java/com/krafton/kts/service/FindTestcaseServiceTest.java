package com.krafton.kts.service;

import com.krafton.kts.domains.entity.KtsTestcase;
import com.krafton.kts.interfaces.repository.testcase.TestcaseInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindTestcaseServiceTest {

    private FindTestcaseService findTestcaseService;

    @Mock
    private TestcaseInterface testcaseInterface;

    @BeforeEach
    public void beforeEach(){
        this.testcaseInterface = mock(TestcaseInterface.class);
        this.findTestcaseService = new FindTestcaseService(this.testcaseInterface);
    }

    @Test
    public void findTestcase() {
        //given
        KtsTestcase testcase = new KtsTestcase();
        testcase.setTestcaseGuid("guid1");
        given(this.testcaseInterface.findTestcase("guid1")).willReturn(testcase);

        //when
        KtsTestcase res = this.findTestcaseService.findTestcase("guid1");

        //then
        assertEquals(res.getTestcaseGuid(), testcase.getTestcaseGuid());
    }

    @Test
    public void findAllTestcase() {
        //given
        List<KtsTestcase> list = new ArrayList<>();
        KtsTestcase testcase = new KtsTestcase();
        testcase.setTestcaseGuid("guid1");
        list.add(testcase);
        given(this.testcaseInterface.findAll()).willReturn(list);

        //when
        List<KtsTestcase> res = this.findTestcaseService.findAllTestcase();

        //then
        assertEquals(res.stream().count(), list.stream().count());
        for(int i = 0; i < list.stream().count(); i++){
            assertEquals(res.get(i).getTestcaseGuid(), list.get(i).getTestcaseGuid());
        }
    }
}
