package com.work.kaka.service;

import com.work.kaka.model.Requirement;
import java.util.List;

public interface RequirementService {

    Requirement createRequirement(Requirement requirement);

    List<Requirement> getAllRequirementsByCommunityId(Long communityId);

    Requirement getRequirementById(Long id) throws Exception;

    Requirement updateRequirement(Long id, Requirement requirement) throws Exception;

    void deleteRequirement(Long id);
}
