package com.pokeapi.controller;

import com.pokeapi.dto.PokemonRequest;
import com.pokeapi.dto.PokemonResponse;
import com.pokeapi.service.PokeAPIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PokeAPIControllerImpl implements PokeAPIController{
  private final PokeAPIService service;
  @Override
  public Mono<ResponseEntity<PokemonResponse>> save(PokemonRequest request) {
    return Mono.defer(() -> service.save(request))
      .subscribeOn(Schedulers.parallel())
      .map(response -> ResponseEntity.status(201).body(response))
      .doOnError(err -> log.error("Failed save a new pokemon - {}", err.getMessage()))
      .doOnNext(it -> log.info("New pokemon successfully registered - {}", it));
  }

  @Override
  public Mono<ResponseEntity<PokemonResponse>> registerByDexNumber(Integer dexNumber) {
    return Mono.defer(() -> service.registerByDexNumber(dexNumber))
      .subscribeOn(Schedulers.parallel())
      .map(response -> ResponseEntity.status(201).body(response))
      .doOnError(err -> log.error("Failed register pokemon - {}", err.getMessage()))
      .doOnNext(it -> log.info("Pokemon successfully registered - {}", it));
  }

  @Override
  public Mono<ResponseEntity<PokemonResponse>> registerByName(String name) {
    return Mono.defer(() -> service.registerByName(name))
      .subscribeOn(Schedulers.parallel())
      .map(response -> ResponseEntity.status(201).body(response))
      .doOnError(err -> log.error("Failed register pokemon - {}", err.getMessage()))
      .doOnNext(it -> log.info("Pokemon successfully registered - {}", it));
  }

  @Override
  public Mono<ResponseEntity<List<PokemonResponse>>> getAll() {
    return Flux.defer(service::getAll)
      .subscribeOn(Schedulers.parallel()).collectList()
      .map(response -> ResponseEntity.ok().body(response))
      .doOnError(err -> log.error("Failed to get pokemon all pokemons - {}", err.getMessage()))
      .doOnNext(it -> log.info("Pokemons successfully founded - {}", it));
  }

  @Override
  public Mono<ResponseEntity<List<PokemonResponse>>> getAllFake() {
    return Flux.defer(service::getAllFake)
      .subscribeOn(Schedulers.parallel()).collectList()
      .map(response -> ResponseEntity.ok().body(response))
      .doOnError(err -> log.error("Failed to get pokemon all pokemons - {}", err.getMessage()))
      .doOnNext(it -> log.info("Pokemons successfully founded - {}", it));
  }

  @Override
  public Mono<ResponseEntity<List<PokemonResponse>>> getByDexNumber(Integer dexNumber) {
    return Flux.defer(() -> service.getByDexNumber(dexNumber))
      .subscribeOn(Schedulers.parallel()).collectList()
      .map(response -> ResponseEntity.ok().body(response))
      .doOnError(err -> log.error("Failed to get pokemon by dex number - {}", err.getMessage()))
      .doOnNext(it -> log.info("Pokemon successfully founded - {}", it));
  }

  @Override
  public Mono<ResponseEntity<List<PokemonResponse>>> searchByName(String name) {
    return Flux.defer(() -> service.searchByName(name))
      .subscribeOn(Schedulers.parallel()).collectList()
      .map(response -> ResponseEntity.ok().body(response))
      .doOnError(err -> log.error("Failed to search pokemon by name - {}", err.getMessage()))
      .doOnNext(it -> log.info("Pokemons successfully founded - {}", it));
  }

  @Override
  public Mono<ResponseEntity<PokemonResponse>> update(PokemonRequest request, String id) {
    return Mono.defer(() -> service.update(request, id))
      .subscribeOn(Schedulers.parallel())
      .map(response -> ResponseEntity.status(200).body(response))
      .doOnError(err -> log.error("Failed to update pokemon - {}", err.getMessage()))
      .doOnNext(it -> log.info("Pokemon successfully updated - {}", it));
  }

  @Override
  public Mono<ResponseEntity<PokemonResponse>> getById(String id) {
    return Mono.defer(() -> service.getById(id))
      .subscribeOn(Schedulers.parallel())
      .map(response -> ResponseEntity.ok().body(response))
      .doOnError(err -> log.error("Failed to get pokemon by register ID - {}", err.getMessage()))
      .doOnNext(it -> log.info("Pokemon successfully founded - {}", it));
  }

  @Override
  public Mono<ResponseEntity<Void>> deleteById(String id) {
    return Mono.defer(() -> service.deleteById(id))
      .subscribeOn(Schedulers.parallel())
      .map(response -> ResponseEntity.ok().body(response))
      .doOnError(err -> log.error("Failed to delete pokemon by register ID - {}", err.getMessage()))
      .doOnNext(it -> log.info("Pokemon successfully deleted - {}", it));
  }
}
