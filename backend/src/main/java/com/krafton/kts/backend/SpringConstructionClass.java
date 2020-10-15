package com.krafton.kts.backend;

import com.krafton.kts.backend.action.repository.RepoJdbc_action;
import com.krafton.kts.backend.action.repository.Repo_action;
import com.krafton.kts.backend.action.service.ServiceImpl_action;
import com.krafton.kts.backend.action.service.Service_action;
import com.krafton.kts.backend.action.repository.RepoJdbc_property;
import com.krafton.kts.backend.action.repository.Repo_property;
import com.krafton.kts.backend.action.service.ServiceImpl_property;
import com.krafton.kts.backend.action.service.Service_property;
import com.krafton.kts.backend.test.service.ServiceImpl_test;
import com.krafton.kts.backend.test.service.Service_test;
import com.krafton.kts.backend.test.service.internal.FindTestService;
import com.krafton.kts.backend.test.service.internal.FindTestServiceJDBC;
import com.krafton.kts.backend.test.service.internal.RemoveTestService;
import com.krafton.kts.backend.test.service.internal.RemoveTestServiceJDBC;
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
    public RemoveTestService removeTest(){
        return new RemoveTestServiceJDBC(dataSource);
    }
    @Bean
    public FindTestService findTestService(){
        return new FindTestServiceJDBC(dataSource);
    }
    @Bean
    public Service_test service_test(){
        return new ServiceImpl_test(removeTest(), findTestService());
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

    //action
    @Bean
    public Repo_action repo_action(){
        return new RepoJdbc_action(dataSource);
    }
    @Bean
    public Service_action service_action(){
        return new ServiceImpl_action(repo_action());
    }

    //property
    @Bean
    public Repo_property repo_property(){
        return new RepoJdbc_property(dataSource);
    }
    @Bean
    public Service_property service_property(){
        return new ServiceImpl_property(repo_property());
    }
}
