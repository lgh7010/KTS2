package com.krafton.kts.backend.action.service;

import com.krafton.kts.backend.action.domain.KTS_PROPERTY;
import com.krafton.kts.backend.action.domain.KTS_PROPERTY_TEMPLETE;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Service_property {
    List<KTS_PROPERTY> findProperty(String actionGuid);
    List<KTS_PROPERTY_TEMPLETE> findPropertyTemplete(String actionId);
    void saveProperties(List<KTS_PROPERTY> list, String actionGuid, String actionId);
}
