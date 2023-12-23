package com.pokeapi.service;

import com.pokeapi.dto.PokemonRequest;
import com.pokeapi.dto.PokemonResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PokeAPIService {
  Mono<PokemonResponse> save(PokemonRequest request);
  Mono<Void> registerByDexNumber(Integer id);
  Mono<Void> registerByName(String name);
  Flux<PokemonResponse> getAll();
  Flux<PokemonResponse> getAllFake();
  Flux<PokemonResponse> getByDexNumber(Integer dexNumber);
  Flux<PokemonResponse> searchByName(String name);
  Mono<PokemonResponse> update(PokemonRequest request, String id);
  Mono<PokemonResponse> getById(String id);
  Mono<Void> deleteById(String id);
}
