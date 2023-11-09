package com.pokeapi.model;

import com.pokeapi.domain.Ability;
import com.pokeapi.domain.Move;
import com.pokeapi.domain.Stat;
import com.pokeapi.domain.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document(collection = "pokemons")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Pokemon {
  @Id
  private String id;
  private Integer dexNumber;
  private String name;
  private Float height;
  private Float weight;
  private Integer baseExp;
  private List<Ability> abilities;
  private List<Type> types;
  private List<Stat> stats;
  private List<Move> moves;
  private List<String> images;
  private Instant createdOn;
  private Instant modifiedOn;
  private Boolean isAvailable;

  public Pokemon(Integer dexNumber, String name, Float height, Float weight, Integer baseExp,
                 List<Ability> abilities, List<Type> types, List<Stat> stats, List<Move> moves, List<String> images,
                 Instant createdOn, Instant modifiedOn, Boolean isAvailable) {
    this.dexNumber = dexNumber;
    this.name = name;
    this.height = height;
    this.weight = weight;
    this.baseExp = baseExp;
    this.abilities = abilities;
    this.types = types;
    this.stats = stats;
    this.moves = moves;
    this.images = images;
    this.createdOn = createdOn;
    this.modifiedOn = modifiedOn;
    this.isAvailable = isAvailable;
  }
}
