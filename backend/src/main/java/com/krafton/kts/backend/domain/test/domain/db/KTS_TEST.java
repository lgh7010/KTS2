package com.krafton.kts.backend.domain.test.domain.db;

import lombok.Data;

@Data
public class KTS_TEST {
    private int testSeq;
    private String name;
    private String description;
    private String deleted;
}
