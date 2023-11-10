package com.pokeapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Ability {
  private Byte slot;
  private String name;
  private Boolean hidden;
}
