package com.krafton.kts.backend.action.service;

import com.krafton.kts.backend.action.domain.KTS_PROPERTY;
import com.krafton.kts.backend.action.domain.KTS_PROPERTY_TEMPLETE;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Service_property {
    List<KTS_PROPERTY> findProperty(String ACTION_GUID);
    List<KTS_PROPERTY_TEMPLETE> findPropertyTemplete(String ACTION_ID);
    void saveProperties(List<KTS_PROPERTY> list, String ACTION_GUID, String ACTION_ID);
}
