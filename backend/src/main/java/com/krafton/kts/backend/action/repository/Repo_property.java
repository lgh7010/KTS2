package com.krafton.kts.backend.action.repository;

import com.krafton.kts.backend.action.domain.KTS_PROPERTY;
import com.krafton.kts.backend.action.domain.KTS_PROPERTY_TEMPLETE;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Repo_property {
    List<KTS_PROPERTY> findProperty(String actionGuid);
    List<KTS_PROPERTY_TEMPLETE> findPropertyTemplete(String actionId);
    void saveProperties(List<KTS_PROPERTY> list, String actionGuid, String actionId);
}
