package com.pokeapi.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MoveInfoDTO(@JsonProperty("move")
                           MoveDTO moveDTO) {

}
