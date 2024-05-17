package com.work.kaka.impl;
import com.work.kaka.model.Community;
import com.work.kaka.model.User;
import com.work.kaka.repository.CommunityRepository;
import com.work.kaka.repository.UserRepository;
import com.work.kaka.service.CommunityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private UserRepository userRepository; // Needed for membership management

    // Create a new Community
    @Override
    public Community createCommunity(Community community) throws Exception {
        // Validation: Check if community with the same name already exists
        if (communityRepository.existsByCommunityName(community.getCommunityName())) {
            // Handle exception as needed
            throw new Exception("Community with this name already exists.");
//            throw new CommunityAlreadyExistsException("Community with this name already exists.");
        }

        // Set creation date (optional, if not already set in the controller)
        community.setCommunityDateOfCreation(LocalDate.now());

        return communityRepository.save(community);
    }

    @Override
    public Community getCommunityById(Long communityId) throws Exception {
        return communityRepository.findById(communityId)
                .orElseThrow(() -> new Exception("Community not found with id " + communityId));
    }

    @Override
    public Community updateCommunity(Long communityId, Community updatedCommunity) throws Exception {
        Community existingCommunity = getCommunityById(communityId);

        // Update fields (consider which fields are updatable)
        existingCommunity.setCommunityName(updatedCommunity.getCommunityName());
        existingCommunity.setCommunityDescription(updatedCommunity.getCommunityDescription());
        // ... (update other fields as needed)

        return communityRepository.save(existingCommunity);
    }

    @Override
    public void deleteCommunity(Long communityId) throws Exception {
        // Check if community exists
        getCommunityById(communityId);
        communityRepository.deleteById(communityId);
    }

    // Get all communities with pagination
    @Override
    public Page<Community> getAllCommunities(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return communityRepository.findAll(pageable);
    }

    @Override
    public void joinCommunity(Long communityId) {

    }

    @Override
    public void leaveCommunity(Long communityId) {

    }

    // Join a community
    @Override
    public void joinCommunity(Long communityId, Long userId) throws Exception {
        Community community = getCommunityById(communityId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found with id " + userId));

        community.getCommunityMembers().add(user);
        communityRepository.save(community);
    }

    // Leave a community
    @Override
    public void leaveCommunity(Long communityId, Long userId) throws Exception {
        Community community = getCommunityById(communityId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found with id " + userId));

        community.getCommunityMembers().remove(user);
        communityRepository.save(community);
    }

    // ... other methods (createPost, getPosts, etc.)
}

