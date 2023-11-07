package com.pokeapi.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OfficialArtworkDTO {
  @JsonProperty("front_default")
  private String imageDefaultUrl;
  @JsonProperty("front_shiny")
  private String ImageShinyUrl;
}
