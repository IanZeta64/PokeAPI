package com.pokeapi.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pokeapi.domain.Stat;
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
  private Integer height;
  @JsonProperty("weight")
  private Integer weight;
  @JsonProperty("sprites")
  private SpritesDTO imageUrlDefault;
  @JsonProperty("types")
  private List<TypeInfoDTO> types;
  @JsonProperty("moves")
  private  List<MoveInfoDTO> moves;
  @JsonProperty("stats")
  private List<StatsInfoDTO> stats;
  @JsonProperty("is_default")
  private Boolean isDefault;

}
