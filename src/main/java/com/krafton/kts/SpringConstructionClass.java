package com.krafton.kts;

import com.krafton.kts.repository.testinfo.TestInfoRepo;
import com.krafton.kts.repository.testinfo.TestInfoRepo_jdbc;
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
        return new TestInfoRepo_jdbc(dataSource);
    }
}
