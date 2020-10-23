package com.krafton.kts.test.services;

import com.krafton.kts.domain.test.command.AddTestCommand;
import com.krafton.kts.domain.test.command.RemoveTestCommand;
import com.krafton.kts.domain.test.db.KTS_TEST;
import com.krafton.kts.test.interfaces.KtsTestInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KtsTestService {

    private final KtsTestInterface testInterface;

    public KTS_TEST findTest(String testGuid) {
        return this.testInterface.findTest(testGuid);
    }
    public List<KTS_TEST> findAllTest() {
        return this.testInterface.findAllTest();
    }
    public void removeTest(RemoveTestCommand command) {
        this.testInterface.removeTest(command);
    }
    public void addTest(AddTestCommand command) {
        this.testInterface.addTest(command);
    }
}
