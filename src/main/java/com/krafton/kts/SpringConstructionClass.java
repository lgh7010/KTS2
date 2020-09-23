package com.krafton.kts;

import com.krafton.kts.module_repository.testinfo.TestInfoRepo;
import com.krafton.kts.module_repository.testinfo.TestInfoRepo_jdbc;
import com.krafton.kts.module_repository.testinfo.TestInfoRepo_jpa;
import com.krafton.kts.module_testlist.service.TestListService;
import com.krafton.kts.module_testlist.service.TestListServiceImpl;
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
        //return new TestInfoRepo_jpa();
        return new TestInfoRepo_jdbc(dataSource);
    }

    @Bean
    public TestListService testListService(){
        return new TestListServiceImpl(testInfoRepo());
    }
}
