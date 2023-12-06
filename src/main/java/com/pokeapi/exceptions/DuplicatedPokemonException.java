package com.pokeapi.exceptions;

public class DuplicatedPokemonException extends RuntimeException {
  public DuplicatedPokemonException(String message) {
    super(message);
  }
}
