package com.work.kaka.dto;

import com.work.kaka.model.Requirement;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequirementDTO {

    private Long requirementId;
    private String description;
    private double budget;
    private LocalDate serviceNeededDate;
    private String location;
    private Requirement.RequirementStatus status;

    // Optional fields (consider including only if needed for specific use cases)
    private String postedBy; // Username or identifier of the user who created the requirement
    private Long communityId; // ID of the community associated with the requirement

    public RequirementDTO(Requirement requirement) {
        this.requirementId = requirement.getRequirementId();
        this.description = requirement.getDescription();
        this.budget = requirement.getBudget();
        this.serviceNeededDate = requirement.getServiceNeededDate();
        this.location = requirement.getLocation();
        this.status = requirement.getStatus();
        // Include optional fields if needed
    }
}
