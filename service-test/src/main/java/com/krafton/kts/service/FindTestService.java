package com.krafton.kts.service;

import com.krafton.kts.domains.entity.KtsTest;
import com.krafton.kts.interfaces.repository.test.TestInterface;
import com.krafton.kts.interfaces.service.FindTestServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindTestService implements FindTestServiceInterface {

    private final TestInterface testInterface;

    @Override
    public KtsTest findTest(String testGuid) {
        return this.testInterface.findTest(testGuid);
    }
    @Override
    public List<KtsTest> findAllTest() {
        return this.testInterface.findAllTest();
    }

}
