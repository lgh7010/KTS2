package com.krafton.kts.service;

import com.krafton.kts.domains.entity.KTS_TEST;
import com.krafton.kts.interfaces.repository.test.TestInterface;
import com.krafton.kts.interfaces.repository.testreltestcase.TestRelTestcaseInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TestListComponentService.class)
public class TestListComponentServiceTest {
    @Autowired
    private TestListComponentService testListComponentService;

    @MockBean
    private TestInterface testInterface;
    @MockBean
    private TestRelTestcaseInterface testRelTestcaseInterface;

    @Test
    public void findAllTest(){
        //given
        List<KTS_TEST> list = new ArrayList<>();
        KTS_TEST test = new KTS_TEST();
        test.setName("name1");
        list.add(test);
        given(this.testListComponentService.findAllTest()).willReturn(list);

        //when
        List<KTS_TEST> ret = this.testListComponentService.findAllTest();

        //then
        assertEquals(list.stream().count(), ret.stream().count());
        for(int i = 0; i < list.stream().count(); i++){
            assertEquals(list.get(i).getName(), ret.get(i).getName());
        }
    }
    @Test
    public void removeTest(){    }
}
