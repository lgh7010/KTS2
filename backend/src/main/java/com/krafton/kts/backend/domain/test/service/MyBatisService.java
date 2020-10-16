package com.krafton.kts.backend.domain.test.service;

import com.krafton.kts.backend.domain.test.domain.db.KTS_TEST;
import com.krafton.kts.backend.domain.test.domain.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBatisService {

    private TestMapper testMapper;

    @Autowired
    public MyBatisService(TestMapper testMapper){
        this.testMapper = testMapper;
    }

    public List<KTS_TEST> getTest(){
        return this.testMapper.getTest();
    }
}
