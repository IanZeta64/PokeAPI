package com.pokeapi.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StatsInfoDTO(@JsonProperty("base_stat")
                           Integer baseStat,
                           @JsonProperty("stat")
                           StatDTO statDTO) {

}
