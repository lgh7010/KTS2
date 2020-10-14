package com.krafton.kts.backend.property.repository;

import com.krafton.kts.backend.property.domain.KTS_PROPERTY;
import com.krafton.kts.backend.property.domain.KTS_PROPERTY_TEMPLETE;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Repo_property {
    List<KTS_PROPERTY> findProperty(String ACTION_GUID);
    List<KTS_PROPERTY_TEMPLETE> findPropertyTemplete(String ACTION_ID);
    void saveProperties(List<KTS_PROPERTY> list, String ACTION_GUID, String ACTION_ID);
}
