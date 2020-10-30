package com.krafton.kts.service;

import com.krafton.kts.domains.entity.KtsActionPropertyTemplate;
import com.krafton.kts.interfaces.repository.property.PropertyInterface;
import com.krafton.kts.interfaces.service.FindPropertyServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindPropertyService implements FindPropertyServiceInterface {

    private final PropertyInterface propertyInterface;

    @Override
    public List<KtsActionPropertyTemplate> getPropertyTemplate(String actionId) {
        return this.propertyInterface.getPropertyTemplate(actionId);
    }
}
