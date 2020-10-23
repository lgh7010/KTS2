package com.krafton.kts.frontend;

import com.krafton.kts.backend.service.CrossDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class KTSController {

    private final CrossDomainService ktsService;

    //transactional

}
