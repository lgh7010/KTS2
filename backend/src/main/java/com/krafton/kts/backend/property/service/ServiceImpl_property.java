package com.krafton.kts.backend.property.service;

import com.krafton.kts.backend.property.domain.KTS_PROPERTY;
import com.krafton.kts.backend.property.domain.KTS_PROPERTY_TEMPLETE;
import com.krafton.kts.backend.property.repository.Repo_property;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ServiceImpl_property implements Service_property {

    private Repo_property repo_property;

    @Autowired
    public ServiceImpl_property(Repo_property repo_property){
        this.repo_property = repo_property;
    }

    @Override
    public List<KTS_PROPERTY> findProperty(String ACTION_GUID) {
        return this.repo_property.findProperty(ACTION_GUID);
    }

    @Override
    public List<KTS_PROPERTY_TEMPLETE> findPropertyTemplete(String ACTION_ID) {
        return this.repo_property.findPropertyTemplete(ACTION_ID);
    }

    @Override
    public void saveProperties(List<KTS_PROPERTY> list, String ACTION_GUID, String ACTION_ID) {
        this.repo_property.saveProperties(list, ACTION_GUID, ACTION_ID);
    }
}
