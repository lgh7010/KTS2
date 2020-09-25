package com.krafton.kts.backend;

import com.krafton.kts.backend.module_repository.testinfo.TestInfoRepo;
import com.krafton.kts.backend.module_repository.testinfo.TestInfoRepo_jdbc;
import com.krafton.kts.backend.module_testcaselist.service.TestcaseListService;
import com.krafton.kts.backend.module_testcaselist.service.TestcaseListServiceImpl;
import com.krafton.kts.backend.module_testlist.service.TestListService;
import com.krafton.kts.backend.module_testlist.service.TestListServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConstructionClass {

    private final DataSource dataSource;

    public SpringConstructionClass(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Bean
    public TestInfoRepo testInfoRepo(){
        //return new TestInfoRepo_jpa();//일단 jdbc 사용
        return new TestInfoRepo_jdbc(dataSource);
    }

    @Bean
    public TestListService testListService(){
        return new TestListServiceImpl(testInfoRepo());
    }

    @Bean
    public TestcaseListService testcaseListService(){
        return new TestcaseListServiceImpl(testInfoRepo());
    }
}
