package com.krafton.kts.backend;

import com.krafton.kts.backend.action.service.ActionService;
import com.krafton.kts.backend.action.service.ActionServiceImpl;
import com.krafton.kts.backend.action.service.internal.*;
import com.krafton.kts.backend.test.service.TestServiceImpl;
import com.krafton.kts.backend.test.service.TestService;
import com.krafton.kts.backend.test.service.internal.FindTestService;
import com.krafton.kts.backend.test.service.internal.FindTestServiceJDBC;
import com.krafton.kts.backend.test.service.internal.RemoveTestService;
import com.krafton.kts.backend.test.service.internal.RemoveTestServiceJDBC;
import com.krafton.kts.backend.test_rel_testcase.service.TestRelTestcaseServiceImpl;
import com.krafton.kts.backend.test_rel_testcase.service.TestRelTestcaseService;
import com.krafton.kts.backend.test_rel_testcase.service.internal.FindTestRelTestcaseService;
import com.krafton.kts.backend.test_rel_testcase.service.internal.FindTestRelTestcaseServiceJDBC;
import com.krafton.kts.backend.test_rel_testcase.service.internal.SaveTestRelTestcaseService;
import com.krafton.kts.backend.test_rel_testcase.service.internal.SaveTestRelTestcaseServiceJDBC;
import com.krafton.kts.backend.testcase.service.TestcaseService;
import com.krafton.kts.backend.testcase.service.TestcaseServiceImpl;
import com.krafton.kts.backend.testcase.service.internal.*;
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
    public TestService service_test(){
        return new TestServiceImpl(
                removeTest(),
                findTestService()
        );
    }
    @Bean
    public RemoveTestService removeTest(){
        return new RemoveTestServiceJDBC(dataSource);
    }
    @Bean
    public FindTestService findTestService(){
        return new FindTestServiceJDBC(dataSource);
    }

    //testcase
    @Bean
    public TestcaseService service_testcase(){
        return new TestcaseServiceImpl(
                addTestcaseService(),
                findTestcaseService(),
                removeTestcaseService()
        );
    }
    @Bean
    public AddTestcaseService addTestcaseService(){
        return new AddTestcaseServiceJDBC(dataSource);
    }
    @Bean
    public FindTestcaseService findTestcaseService(){
        return new FindTestcaseServiceJDBC(dataSource);
    }
    @Bean
    public RemoveTestcaseService removeTestcaseService(){
        return new RemoveTestcaseServiceJDBC(dataSource);
    }

    //test_rel_testcase
    @Bean
    public TestRelTestcaseService service_test_rel_testcase(){
        return new TestRelTestcaseServiceImpl(
                saveTestRelTestcaseService(),
                findTestRelTestcaseService()
        );
    }
    @Bean
    public SaveTestRelTestcaseService saveTestRelTestcaseService(){
        return new SaveTestRelTestcaseServiceJDBC(dataSource);
    }
    @Bean
    public FindTestRelTestcaseService findTestRelTestcaseService(){
        return new FindTestRelTestcaseServiceJDBC(dataSource);
    }

    //action & property
    @Bean
    public ActionService actionService(){
        return new ActionServiceImpl(
                findActionService(),
                findPropertyService(),
                getPropertyTemplateService(),
                getActionTemplateService(),
                saveActionService(),
                savePropertyService()
        );
    }
    @Bean
    public FindActionService findActionService(){
        return new FindActionServiceJDBC(dataSource);
    }
    @Bean
    public FindPropertyService findPropertyService(){
        return new FindPropertyServiceJDBC(dataSource);
    }
    @Bean
    public GetActionTemplateService getActionTemplateService(){
        return new GetActionTemplateServiceJDBC(dataSource);
    }
    @Bean
    public GetPropertyTemplateService getPropertyTemplateService(){
        return new GetPropertyTemplateServiceJDBC(dataSource);
    }
    @Bean
    public SaveActionService saveActionService(){
        return new SaveActionServiceJDBC(dataSource);
    }
    @Bean
    public SavePropertyService savePropertyService(){
        return new SavePropertyServiceJDBC(dataSource);
    }
}
