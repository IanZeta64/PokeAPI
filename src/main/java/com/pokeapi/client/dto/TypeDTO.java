package com.pokeapi.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TypeDTO {
  @JsonProperty("name")
  private String name;
  @JsonProperty("url")
  private String url;
}
