package com.krafton.kts.backend.interfaces;

import com.krafton.kts.domain.test.command.AddTestCommand;
import com.krafton.kts.domain.test.command.RemoveTestCommand;
import com.krafton.kts.domain.test.db.KTS_TEST;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestInterface {

    //우선 메시지 큐는 다루지 않는다. 우선은 MS간 연결은 Http호출로 한다.
    //각각의 MS는 각각의 도메인의 CURD를 담당한다.
    //웹앱이 요구하는것이 하나의 도메인 내에서 완료될 수 있는 작업이면, 웹앱은 바로 그 MS의 컨트롤러에 요청을 하고, 응답을 받아 보여주면 그만이다.
    //웹앱이 요구하는것이 여러개의 도메인에 영향을 미치는 작업이면, 웹앱은 자신에게 달려있는 (이것도 추후 분리) KTSController로 요청을 하고,
    //이 컨트롤러는 KTSService(backend프로젝트에 속하는 - 이것도 하나의 MS일 뿐이다)의 해당 메소드를 호출한다.
    //KTSService는 각각의 도메인 인터페이스 메소드를 호출하는데, 이 인터페이스들은 결국 각각의 도메인 MS에게 요청을 날리는(Http든 메시지든) 역할을 한다.
    //따라서, backend프로젝트의 각종 도메인의 interface들에는, 결국 KTSService에서 트랜잭셔널하게 호출하는 인터페이스 함수들을 넣으면 된다.

    public void removeTest(RemoveTestCommand command){

    }
    public void addTest(AddTestCommand command){

    }
    public KTS_TEST findTest(String testGuid){
        return null;
    }
}
