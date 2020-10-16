package com.krafton.kts.backend;

import com.krafton.kts.backend.action.service.ActionService;
import com.krafton.kts.backend.action.service.ActionServiceImpl;
import com.krafton.kts.backend.action.service.interfaces.*;
import com.krafton.kts.backend.test.service.TestServiceImpl;
import com.krafton.kts.backend.test.service.TestService;
import com.krafton.kts.backend.test.service.interfaces.TestInterface;
import com.krafton.kts.backend.test.service.interfaces.TestInterfaceJDBC;
import com.krafton.kts.backend.test_rel_testcase.service.TestRelTestcaseServiceImpl;
import com.krafton.kts.backend.test_rel_testcase.service.TestRelTestcaseService;
import com.krafton.kts.backend.test_rel_testcase.service.interfaces.TestRelTestcaseInterface;
import com.krafton.kts.backend.test_rel_testcase.service.interfaces.TestRelTestcaseInterfaceJDBC;
import com.krafton.kts.backend.testcase.service.TestcaseService;
import com.krafton.kts.backend.testcase.service.TestcaseServiceImpl;
import com.krafton.kts.backend.testcase.service.interfaces.*;
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
        return new TestInterfaceJDBC(dataSource);
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
}
