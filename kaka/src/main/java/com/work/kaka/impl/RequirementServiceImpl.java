package com.work.kaka.impl;

import com.work.kaka.model.Requirement;
import com.work.kaka.repository.RequirementRepository;
import com.work.kaka.service.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RequirementServiceImpl implements RequirementService {

    @Autowired
    private RequirementRepository requirementRepository;

    @Override
    public Requirement createRequirement(Requirement requirement) {
        requirement.setStatus(Requirement.RequirementStatus.OPEN); // Set default to Open
        return requirementRepository.save(requirement);
    }

    @Override
    public List<Requirement> getAllRequirementsByCommunityId(Long communityId) {
        return requirementRepository.findAll();
    }

    @Override
    public Requirement getRequirementById(Long id) throws Exception {
        return requirementRepository.findById(id)
                .orElseThrow(() -> new Exception("Requirement not found with id: " + id));
    }

    @Override
    public Requirement updateRequirement(Long id, Requirement requirement) throws Exception {
        Requirement existingRequirement = getRequirementById(id);
        // Update relevant fields (consider using a mapper)
        existingRequirement.setDescription(requirement.getDescription());
        existingRequirement.setBudget(requirement.getBudget());
        existingRequirement.setServiceNeededDate(requirement.getServiceNeededDate());
        existingRequirement.setLocation(requirement.getLocation());
        existingRequirement.setStatus(requirement.getStatus()); // Update status if provided
        return requirementRepository.save(existingRequirement);
    }

    @Override
    public void deleteRequirement(Long id) {
        requirementRepository.deleteById(id);
    }
}

