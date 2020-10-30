package com.krafton.kts.service;

import com.krafton.kts.domains.entity.KtsTest;
import com.krafton.kts.interfaces.repository.test.TestInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

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

        //when

        //then
    }

    @Test
    public void findAllTest() {
        //given

        //when

        //then
    }
}
