package com.work.kaka.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.work.kaka.model.*;
import com.work.kaka.service.CommunityService;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/api/communities") // Consider versioning like /api/v1/communities
public class CommunityController {

    @Autowired
    private CommunityService communityService; // Inject your CommunityService

    @PostMapping
    @PreAuthorize("hasAuthority('USER')") // Only logged-in users can create communities
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Community> createCommunity(@RequestBody Community community) throws Exception {
        Community createdCommunity = communityService.createCommunity(community);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCommunity);
    }

    @GetMapping("/{communityId}")
    public ResponseEntity<Community> getCommunityById(@PathVariable Long communityId) throws Exception {
        Community community = communityService.getCommunityById(communityId);
        return ResponseEntity.ok(community);
    }

    @PutMapping("/{communityId}")
    @PreAuthorize("@communityAuthorizationService.canUpdateCommunity(authentication, #communityId)") // Custom authorization
    public ResponseEntity<Community> updateCommunity(@PathVariable Long communityId, @RequestBody Community community) throws Exception {
        Community updatedCommunity = communityService.updateCommunity(communityId, community);
        return ResponseEntity.ok(updatedCommunity);
    }

    @DeleteMapping("/{communityId}")
    @PreAuthorize("@communityAuthorizationService.canDeleteCommunity(authentication, #communityId)") // Custom authorization
    public ResponseEntity<?> deleteCommunity(@PathVariable Long communityId) throws Exception {
        communityService.deleteCommunity(communityId);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @GetMapping
    public ResponseEntity<List<Community>> getAllCommunities(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber) {

        Page<Community> communities = communityService.getAllCommunities(pageSize, pageNumber);
        return ResponseEntity.ok(communities.getContent());
    }


    // Additional Endpoints (Examples)
    @PostMapping("/{communityId}/join")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> joinCommunity(@PathVariable Long communityId) {
        communityService.joinCommunity(communityId);
        return ResponseEntity.ok("Joined community successfully");
    }

    @PostMapping("/{communityId}/leave")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> leaveCommunity(@PathVariable Long communityId) {
        communityService.leaveCommunity(communityId);
        return ResponseEntity.ok("Left community successfully");
    }

    // ... other endpoints for managing members, posts, etc.
}

