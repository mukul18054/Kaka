package com.work.kaka.model;

import java.util.List;

public class Community {
    private String communityName;
    private String communityLocation;
    private String communityType;
    private String communitySize;
    private String communityStatus;
    private String communityJoiningFees;
    private String communityDateOfCreation;
    private String communityDescription;
    private List<String> communityOwners; // userId
    private String communityManager;
    private List<String> communityMembers;
    private List<Post> communityPosts;
    private String communityRating;
    private List<String> communitySuggestion;
    private List<String> communityComplaint;
}
