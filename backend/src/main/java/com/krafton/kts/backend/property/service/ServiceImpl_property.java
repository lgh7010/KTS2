package com.krafton.kts.backend.property.service;

import com.krafton.kts.backend.property.repository.Repo_property;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceImpl_property implements Service_property {

    private Repo_property repo_property;

    @Autowired
    public ServiceImpl_property(Repo_property repo_property){
        this.repo_property = repo_property;
    }

}
