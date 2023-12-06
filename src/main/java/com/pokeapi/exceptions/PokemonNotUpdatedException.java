package com.pokeapi.exceptions;

public class PokemonNotUpdatedException extends RuntimeException {
  public PokemonNotUpdatedException(String message) {
    super(message);
  }
}
