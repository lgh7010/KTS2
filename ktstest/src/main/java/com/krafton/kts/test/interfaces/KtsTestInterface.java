package com.krafton.kts.test.interfaces;

import com.krafton.kts.domain.test.command.AddTestCommand;
import com.krafton.kts.domain.test.command.RemoveTestCommand;
import com.krafton.kts.domain.test.db.KTS_TEST;
import com.krafton.kts.test.repository.KtsTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KtsTestInterface {

    private final KtsTestRepository ktsTestRepository;

    public KTS_TEST findTest(String testGuid) {
        return this.ktsTestRepository.findTest(testGuid);
    }

    public List<KTS_TEST> findAllTest() {
        return this.ktsTestRepository.findAllTest();
    }

    public void removeTest(RemoveTestCommand command) {
        this.ktsTestRepository.removeTest(command);
    }

    public void addTest(AddTestCommand command) {
        this.ktsTestRepository.addTest(command);
    }
}
