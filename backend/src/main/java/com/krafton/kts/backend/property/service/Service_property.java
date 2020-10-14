package com.krafton.kts.backend.property.service;

import com.krafton.kts.backend.property.domain.KTS_PROPERTY;
import com.krafton.kts.backend.property.domain.KTS_PROPERTY_TEMPLETE;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Service_property {
    List<KTS_PROPERTY> findProperty(String ACTION_GUID);
    List<KTS_PROPERTY_TEMPLETE> findPropertyTemplete(String ACTION_ID);
    void saveProperties(List<KTS_PROPERTY> list, String ACTION_GUID, String ACTION_ID);
}
