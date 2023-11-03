package com.pokeapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("pokemon")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Pokemon {
  @Id
  private String mongoId;
  private Integer dexNumber;
  private String name;
  private Float height;
  private Float weight;
  private Integer baseExp;
  private List<Type> types;
  private List<Move> moves;
  private List<String> images;
}
