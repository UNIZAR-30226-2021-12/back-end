package com.unizar.unozar.core.controller.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FriendListResponse {

  @JsonProperty
  private String token;
  
  @JsonProperty
  private String[] friendIds;

  @JsonProperty
  private String[] alias;
  
  @JsonProperty
  private String[] emails;
  
  @JsonProperty
  private int[] avatarIds;
  
  public FriendListResponse(String token, String[] friendIds, String[] alias, 
      String[] emails, int[] avatarIds){
    this.token = token;
    this.friendIds = friendIds;
    this.alias = alias;
    this.emails = emails;
    this.avatarIds = avatarIds;
  }
  
}
