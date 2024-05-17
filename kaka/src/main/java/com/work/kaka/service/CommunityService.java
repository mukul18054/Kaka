package com.work.kaka.service;

import com.work.kaka.model.Community;
import org.springframework.data.domain.Page;

public interface CommunityService {

    Community createCommunity(Community community) throws Exception;

    Community getCommunityById(Long communityId) throws Exception;

    Community updateCommunity(Long communityId, Community updatedCommunity) throws Exception;

    void deleteCommunity(Long communityId) throws Exception;

    Page<Community> getAllCommunities(int pageSize, int pageNumber);

    // Additional Methods (Examples)
    void joinCommunity(Long communityId);

    void leaveCommunity(Long communityId);

    // Join a community
    void joinCommunity(Long communityId, Long userId) throws Exception;

    // Leave a community
    void leaveCommunity(Long communityId, Long userId) throws Exception;

    // ... other methods as needed for your specific community features ...
}
