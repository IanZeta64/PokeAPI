package com.pokeapi.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PokeDTO {
  @JsonProperty("id")
  private Integer dexNumber;
  @JsonProperty("name")
  private String name;
  @JsonProperty("abilities")
  private List<AbilitiesDTO> abilities;
  @JsonProperty("base_experience")
  private Integer baseExp;
  @JsonProperty("height")
  private Float height;
  @JsonProperty("weight")
  private Float weight;
  @JsonProperty("sprites")
  private SpritesDTO imagesUrl;
  @JsonProperty("types")
  private List<TypeInfoDTO> types;
  @JsonProperty("moves")
  private  List<MoveInfoDTO> moves;
  @JsonProperty("stats")
  private List<StatsInfoDTO> stats;
  @JsonProperty("is_default")
  private Boolean isDefault;
}
