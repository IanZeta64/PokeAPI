package com.pokeapi.model;

import com.pokeapi.domain.Ability;
import com.pokeapi.domain.Move;
import com.pokeapi.domain.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
  private List<Move> moves;
  private List<String> images;
}
