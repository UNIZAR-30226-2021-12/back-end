package com.unizar.unozar.core.controller.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse{

  @JsonProperty
  private String token;
    
  public AuthenticationResponse(String token){
    this.token = token;
  }

}