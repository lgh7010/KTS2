package com.krafton.kts.backend.action.service;

import com.krafton.kts.backend.action.domain.KTS_PROPERTY;
import com.krafton.kts.backend.action.domain.KTS_PROPERTY_TEMPLATE;
import com.krafton.kts.backend.action.repository.Repo_property;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ServiceImpl_property implements Service_property {

    private Repo_property repo_property;

    @Autowired
    public ServiceImpl_property(Repo_property repo_property){
        this.repo_property = repo_property;
    }

    @Override
    public List<KTS_PROPERTY> findProperty(String actionGuid) {
        return this.repo_property.findProperty(actionGuid);
    }

    @Override
    public List<KTS_PROPERTY_TEMPLATE> findPropertyTemplate(String actionId) {
        return this.repo_property.findPropertyTemplate(actionId);
    }

    @Override
    public void saveProperties(List<KTS_PROPERTY> list, String actionGuid, String actionId) {
        this.repo_property.saveProperties(list, actionGuid, actionId);
    }
}
