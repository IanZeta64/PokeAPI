package com.pokeapi.exceptions;

public class PokemonNotCreatedException extends RuntimeException {
  public PokemonNotCreatedException(String message) {
    super(message);
  }
}
