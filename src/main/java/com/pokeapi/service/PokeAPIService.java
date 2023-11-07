package com.pokeapi.service;

import com.pokeapi.client.dto.PokeDTO;
import com.pokeapi.dto.PokemonRequest;
import com.pokeapi.dto.PokemonResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PokeAPIService {
  Mono<PokemonResponse> save(PokemonRequest request);
  Flux<PokemonResponse> getAll();
  Flux<PokeDTO> getByDexNumber(Integer dexNumber);
  Flux<PokemonResponse> getByName(Integer dexNumber);
  Mono<PokemonResponse> update(PokemonRequest request, Integer id);
  Mono<PokemonResponse> getById(Integer id);
  Mono<Void> deleteById(Integer id);
}
