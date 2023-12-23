package com.pokeapi.dto;

import com.pokeapi.domain.Ability;
import com.pokeapi.domain.Move;
import com.pokeapi.domain.Stat;
import com.pokeapi.domain.Type;
import jakarta.validation.constraints.*;

import java.util.List;

public record PokemonRequest(
  @NotNull(message = "Pokemon dex number can not be null")
  @Min(value = 1, message = "The pokemon dex number should be greater than or equal to 1")
  Integer dexNumber,
  @NotBlank(message = "Pokemon name can not be blank")
  @Pattern(regexp = "^[a-zA-Z]+$", message = "The name should only contain letters")
  @Size(max = 10, message = "The pokemon name should have a maximum of 10 letters")
  @Size(min = 1, message = "The pokemon name should have a minimum of 1 letters")
  String name,
  @NotBlank(message = "Pokemon region can not be blank")
  @Pattern(regexp = "^[a-zA-Z]+$", message = "The name should only contain letters")
  @Size(max = 10, message = "The pokemon region should have a maximum of 10 letters")
  @Size(min = 1, message = "The pokemon region should have a minimum of 1 letters")
  String region,

  @DecimalMin(value = "0.01", message = "The pokemon height should have a minimum of 0.01")
  Float height,
  @DecimalMin(value = "0.01", message = "The pokemon weight should have a minimum of 0.01")
  Float weight,
  Integer baseExp,

  List<Ability> abilities,
  @NotNull(message = "Pokemon types can not be blank")
  List<Type> types,
  List<Stat> stats,
  List<Move> moves,
  @NotNull(message = "Pokemon sprites url can not be blank")
  List<String> spritesUrl) {
  public String fullName(){
    return name.concat("-").concat(region);
  }
}


