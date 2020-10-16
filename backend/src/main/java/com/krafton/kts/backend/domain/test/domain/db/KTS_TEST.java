package com.krafton.kts.backend.domain.test.domain.db;

import lombok.Data;

@Data
public class KTS_TEST {
    private String testGuid;
    private String name;
    private String description;
    private String deleted;
}
