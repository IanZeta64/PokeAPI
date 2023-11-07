package com.pokeapi.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AbilitiesDTO {
  @JsonProperty("slot")
  private Byte slot;
  @JsonProperty("ability")
 private AbilityDTO abilityDTO;
  @JsonProperty("is_hidden")
  private Boolean hidden;
}
