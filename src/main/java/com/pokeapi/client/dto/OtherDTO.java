package com.pokeapi.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OtherDTO(@JsonProperty("official-artwork")
                        OfficialArtworkDTO officialArtworkDTO) {

}
