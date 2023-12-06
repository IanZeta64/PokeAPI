package com.pokeapi.exceptions;

public class PokemonNotDeletedException extends RuntimeException{
  public PokemonNotDeletedException(String message) {
    super(message);
  }
}
