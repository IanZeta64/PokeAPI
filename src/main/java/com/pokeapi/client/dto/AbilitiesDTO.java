package com.pokeapi.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AbilitiesDTO(@JsonProperty("slot")
                           Byte slot,
                           @JsonProperty("ability")
                           AbilityDTO abilityDTO,
                           @JsonProperty("is_hidden")
                           Boolean hidden) {

}
