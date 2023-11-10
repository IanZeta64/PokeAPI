package com.pokeapi.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StatDTO(@JsonProperty("name")
                      String name,
                      @JsonProperty("url")
                      String url) {

}
