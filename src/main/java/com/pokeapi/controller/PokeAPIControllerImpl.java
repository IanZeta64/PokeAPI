package com.pokeapi.controller;

import com.pokeapi.client.dto.PokeDTO;
import com.pokeapi.dto.PokemonRequest;
import com.pokeapi.dto.PokemonResponse;
import com.pokeapi.service.PokeAPIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PokeAPIControllerImpl implements PokeAPIController{
  private final PokeAPIService service;
  @Override
  public Mono<PokemonResponse> save(PokemonRequest request) {
    return null;
  }

  @Override
  public Flux<PokemonResponse> getAll() {
    return null;
  }

  @Override
  public Flux<PokeDTO> getByDexNumber(Integer dexNumber) {
    return service.getByDexNumber(dexNumber).subscribeOn(Schedulers.parallel());
  }

  @Override
  public Flux<PokemonResponse> getByName(Integer dexNumber) {
    return null;
  }

  @Override
  public Mono<PokemonResponse> update(PokemonRequest request, String id) {
    return null;
  }

  @Override
  public Mono<PokemonResponse> getById(String id) {
    return null;
  }

  @Override
  public Mono<Void> deleteById(String id) {
    return null;
  }
}
