package com.krafton.kts.dummyclient;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DummyWebClientController {
    @GetMapping("/dummyWebClient")
    public String dummyWebClient(){
        return "dummyWebClient";
    }
}
