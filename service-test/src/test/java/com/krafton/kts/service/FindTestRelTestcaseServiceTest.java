package com.krafton.kts.service;

import com.krafton.kts.domains.derived.TestRelTestcaseJoinTestcase;
import com.krafton.kts.interfaces.repository.derived.DerivedDomainInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class FindTestRelTestcaseServiceTest {

    private FindTestRelTestcaseService findTestRelTestcaseService;

    @Mock
    private DerivedDomainInterface derivedDomainInterface;

    @BeforeEach
    public void beforeEach(){
        this.derivedDomainInterface = mock(DerivedDomainInterface.class);
        this.findTestRelTestcaseService = new FindTestRelTestcaseService(this.derivedDomainInterface);
    }

    @Test
    public void findTestRelTestcaseJoinTestcase() {
        //given
        List<TestRelTestcaseJoinTestcase> list = new ArrayList<>();
        TestRelTestcaseJoinTestcase rel = new TestRelTestcaseJoinTestcase();
        rel.setRelationGuid("GUID1");
        list.add(rel);
        given(this.derivedDomainInterface.findTestRelTestcaseJoinTestcase("GUID1")).willReturn(list);

        //when
        List<TestRelTestcaseJoinTestcase> ret = this.findTestRelTestcaseService.findTestRelTestcaseJoinTestcase("GUID1");

        //then
        assertEquals(list.stream().count(), ret.stream().count());
        for(int i = 0; i < list.stream().count(); i++){
            assertEquals(list.get(i).getRelationGuid(), ret.get(i).getRelationGuid());
        }
    }
}
