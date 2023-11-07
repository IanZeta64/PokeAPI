package com.pokeapi.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MoveInfoDTO {
  @JsonProperty("move")
  private MoveDTO moveDTO;
}
