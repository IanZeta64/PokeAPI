package com.pokeapi.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TypeInfoDTO {
  @JsonProperty("slot")
  private Byte slot;
  @JsonProperty("type")
  private TypeDTO type;
}
