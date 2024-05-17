package com.work.kaka.controller;

import com.work.kaka.model.Requirement;
import com.work.kaka.service.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requirements")
public class RequirementController {

    @Autowired
    private RequirementService requirementService;

    // Create a new Requirement
    @PostMapping("/create")
    public ResponseEntity<Requirement> createRequirement(@RequestBody Requirement requirement) {
        Requirement savedRequirement = requirementService.createRequirement(requirement);
        return ResponseEntity.ok(savedRequirement);
    }

    // Get all Requirements
    @GetMapping("/listRequirements/{id}")
    public ResponseEntity<List<Requirement>> getAllRequirementsByCommunityId(@PathVariable Long communityId) {
        List<Requirement> requirements = requirementService.getAllRequirementsByCommunityId(communityId);
        return ResponseEntity.ok(requirements);
    }

    // Get a Requirement by ID
    @GetMapping("/{id}")
    public ResponseEntity<Requirement> getRequirementById(@PathVariable Long id) throws Exception {
        Requirement requirement = requirementService.getRequirementById(id);
        return ResponseEntity.ok(requirement);
    }

    // Update a Requirement
    @PutMapping("/{id}")
    public ResponseEntity<Requirement> updateRequirement(@PathVariable Long id, @RequestBody Requirement requirement) throws Exception {
        Requirement updatedRequirement = requirementService.updateRequirement(id, requirement);
        return ResponseEntity.ok(updatedRequirement);
    }

    // Delete a Requirement
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequirement(@PathVariable Long id) {
        requirementService.deleteRequirement(id);
        return ResponseEntity.noContent().build();
    }
}
