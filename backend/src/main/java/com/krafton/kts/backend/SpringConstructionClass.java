package com.krafton.kts.backend;

import com.krafton.kts.backend.cross.service.CrossDomainInterface;
import com.krafton.kts.backend.cross.service.CrossDomainInterfaceImpl;
import com.krafton.kts.backend.domain.action.service.ActionService;
import com.krafton.kts.backend.domain.action.service.ActionServiceImpl;
import com.krafton.kts.backend.domain.action.service.impl.*;
import com.krafton.kts.backend.domain.test.service.TestServiceImpl;
import com.krafton.kts.backend.domain.test.service.TestService;
import com.krafton.kts.backend.domain.test.service.impl.TestInterface;
import com.krafton.kts.backend.domain.test.service.impl.mybatis.TestInterfaceMybatis;
import com.krafton.kts.backend.domain.test_rel_testcase.service.TestRelTestcaseServiceImpl;
import com.krafton.kts.backend.domain.test_rel_testcase.service.TestRelTestcaseService;
import com.krafton.kts.backend.domain.test_rel_testcase.service.impl.TestRelTestcaseInterface;
import com.krafton.kts.backend.domain.test_rel_testcase.service.impl.TestRelTestcaseInterfaceJDBC;
import com.krafton.kts.backend.domain.testcase.service.TestcaseService;
import com.krafton.kts.backend.domain.testcase.service.TestcaseServiceImpl;
import com.krafton.kts.backend.domain.testcase.service.impl.*;
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
    public TestService testService(){
        return new TestServiceImpl(
                testInterface()
        );
    }
    @Bean
    public TestInterface testInterface(){
        //return new TestInterfaceJDBC(dataSource); //JDBC 이용
        return new TestInterfaceMybatis();          //Mybatis 이용
    }

    //testcase
    @Bean
    public TestcaseService testcaseService(){
        return new TestcaseServiceImpl(
                testcaseInterface()
        );
    }
    @Bean
    public TestcaseInterface testcaseInterface(){
        return new TestcaseInterfaceJDBC(dataSource);
    }

    //test_rel_testcase
    @Bean
    public TestRelTestcaseService testRelTestcaseService(){
        return new TestRelTestcaseServiceImpl(
                testRelTestcaseInterface()
        );
    }
    @Bean
    public TestRelTestcaseInterface testRelTestcaseInterface(){
        return new TestRelTestcaseInterfaceJDBC(dataSource);
    }

    //action & property
    @Bean
    public ActionService actionService(){
        return new ActionServiceImpl(
                actionInterface(),
                propertyInterface()
        );
    }
    @Bean
    public ActionInterface actionInterface(){
        return new ActionInterfaceJDBC(dataSource);
    }
    @Bean
    public PropertyInterface propertyInterface(){
        return new PropertyInterfaceJDBC(dataSource);
    }

    //cross
    @Bean
    public CrossDomainInterface saveActionService(){
        return new CrossDomainInterfaceImpl(
                actionInterface(),
                testcaseInterface()
        );
    }
}
