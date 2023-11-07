package com.pokeapi.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MoveDTO {
  @JsonProperty("name")
  private String name;

  @JsonProperty("url")
  private String url;
}
