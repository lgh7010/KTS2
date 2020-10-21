package com.krafton.kts.backend;

import com.krafton.kts.backend.domain.action.interfaces.ActionInterface;
import com.krafton.kts.backend.domain.action.interfaces.mybatis.ActionInterfaceMybatis;
import com.krafton.kts.backend.domain.property.interfaces.PropertyInterface;
import com.krafton.kts.backend.domain.property.interfaces.mybatis.PropertyInterfaceMybatis;
import com.krafton.kts.backend.domain.running_action.interfaces.RunningActionInterface;
import com.krafton.kts.backend.domain.running_action.interfaces.mybatis.RunningActionInterfaceMybatis;
import com.krafton.kts.backend.domain.running_property.interfaces.RunningPropertyInterface;
import com.krafton.kts.backend.domain.running_property.interfaces.mybatis.RunningPropertyInterfaceMybatis;
import com.krafton.kts.backend.domain.running_test.interfaces.RunningTestInterface;
import com.krafton.kts.backend.domain.running_test.interfaces.mybatis.RunningTestInterfaceMybatis;
import com.krafton.kts.backend.domain.running_testcase.interfaces.RunningTestcaseInterface;
import com.krafton.kts.backend.domain.running_testcase.interfaces.mybatis.RunningTestcaseInterfaceMybatis;
import com.krafton.kts.backend.domain.test.interfaces.TestInterface;
import com.krafton.kts.backend.domain.test.interfaces.mybatis.TestInterfaceMybatis;
import com.krafton.kts.backend.domain.test_rel_testcase.interfaces.TestRelTestcaseInterface;
import com.krafton.kts.backend.domain.test_rel_testcase.interfaces.mybatis.TestRelTestcaseInterfaceMybatis;
import com.krafton.kts.backend.domain.testcase.interfaces.TestcaseInterface;
import com.krafton.kts.backend.domain.testcase.interfaces.mybatis.TestcaseInterfaceMybatis;
import com.krafton.kts.backend.service.KTSService;
import com.krafton.kts.backend.service.KTSServiceImpl;
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
    public KTSService mySystemService(){
        return new KTSServiceImpl(
                actionInterface(),
                propertyInterface(),
                testcaseInterface(),
                testRelTestcaseInterface(),
                testInterface(),
                runningTestInterface(),
                runningTestcaseInterface(),
                runningActionInterface(),
                runningPropertyInterface()
        );
    }

    @Bean
    public TestInterface testInterface(){
        //return new TestInterfaceJDBC(dataSource);             //JDBC 이용
        return new TestInterfaceMybatis();                      //Mybatis 이용
    }

    @Bean
    public TestcaseInterface testcaseInterface(){
        //return new TestcaseInterfaceJDBC(dataSource);         //JDBC 이용
        return new TestcaseInterfaceMybatis();                  //Mybatis 이용
    }

    @Bean
    public TestRelTestcaseInterface testRelTestcaseInterface(){
        //return new TestRelTestcaseInterfaceJDBC(dataSource);  //JDBC 이용
        return new TestRelTestcaseInterfaceMybatis();           //Mybatis 이용
    }

    @Bean
    public ActionInterface actionInterface(){
        //return new ActionInterfaceJDBC(dataSource);           //JDBC 이용
        return new ActionInterfaceMybatis();                    //Mybatis 이용
    }

    @Bean
    public PropertyInterface propertyInterface(){
        //return new PropertyInterfaceJDBC(dataSource);         //JDBC 이용
        return new PropertyInterfaceMybatis();                  //Mybatis 이용
    }

    @Bean
    public RunningTestInterface runningTestInterface(){
        return new RunningTestInterfaceMybatis();               //Mybatis 이용
    }

    @Bean
    public RunningTestcaseInterface runningTestcaseInterface(){
        return new RunningTestcaseInterfaceMybatis();           //Mybatis 이용
    }

    @Bean
    public RunningActionInterface runningActionInterface(){
        return new RunningActionInterfaceMybatis();             //Mybatis 이용
    }

    @Bean
    public RunningPropertyInterface runningPropertyInterface(){
        return new RunningPropertyInterfaceMybatis();           //Mybatis 이용
    }
}
