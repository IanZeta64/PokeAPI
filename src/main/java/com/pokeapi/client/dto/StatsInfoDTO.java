package com.pokeapi.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatsInfoDTO {
  @JsonProperty("base_stat")
  private Integer baseStat;
  @JsonProperty("stat")
  private StatDTO statDTO;
}
