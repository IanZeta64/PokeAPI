package com.pokeapi.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SpritesDTO(@JsonProperty("other")
                         OtherDTO otherDTO) {

}
