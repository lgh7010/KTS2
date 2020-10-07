package com.krafton.kts.backend;

import com.krafton.kts.backend.test.repository.Repo_test;
import com.krafton.kts.backend.test.repository.RepoJdbc_test;
import com.krafton.kts.backend.test.service.ServiceImpl_test;
import com.krafton.kts.backend.test.service.Service_test;
import com.krafton.kts.backend.test_rel_testcase.repository.RepoJdbc_test_rel_testcase;
import com.krafton.kts.backend.test_rel_testcase.repository.Repo_test_rel_testcase;
import com.krafton.kts.backend.test_rel_testcase.service.ServiceImpl_test_rel_testcase;
import com.krafton.kts.backend.test_rel_testcase.service.Service_test_rel_testcase;
import com.krafton.kts.backend.testcase.repository.RepoJdbc_testcase;
import com.krafton.kts.backend.testcase.repository.Repo_testcase;
import com.krafton.kts.backend.testcase.service.Service_testcase;
import com.krafton.kts.backend.testcase.service.ServiceImpl_testcase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConstructionClass {

    private final DataSource dataSource;

    public SpringConstructionClass(DataSource dataSource){
        this.dataSource = dataSource;
    }

    //test
    @Bean
    public Repo_test repo_test(){
        //return new TestInfoRepo_jpa();//일단 jdbc 사용
        return new RepoJdbc_test(dataSource);
    }
    @Bean
    public Service_test service_test(){
        return new ServiceImpl_test(repo_test());
    }

    //testcase
    @Bean
    public Repo_testcase repo_testcase(){
        return new RepoJdbc_testcase(dataSource);
    }
    @Bean
    public Service_testcase service_testcase(){
        return new ServiceImpl_testcase(repo_testcase());
    }

    //test_rel_testcase
    @Bean
    public Repo_test_rel_testcase repo_test_rel_testcase(){
        return new RepoJdbc_test_rel_testcase(dataSource);
    }
    @Bean
    public Service_test_rel_testcase service_test_rel_testcase(){
        return new ServiceImpl_test_rel_testcase(repo_test_rel_testcase());
    }
}
