package com.pokeapi.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OfficialArtworkDTO(@JsonProperty("front_default")
                                 String imageDefaultUrl,
                                 @JsonProperty("front_shiny")
                                 String ImageShinyUrl) {

}
