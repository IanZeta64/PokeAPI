package com.pokeapi.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TypeInfoDTO(@JsonProperty("slot")
                          Byte slot,
                          @JsonProperty("type")
                          TypeDTO type) {

}
