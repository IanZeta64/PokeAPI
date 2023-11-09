package com.pokeapi.service;

import com.pokeapi.client.PokeAPIExternalClient;
import com.pokeapi.dto.PokemonRequest;
import com.pokeapi.dto.PokemonResponse;
import com.pokeapi.model.Pokemon;
import com.pokeapi.repository.PokeAPIRepository;
import com.pokeapi.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
@Slf4j
public class PokeAPIServiceImpl implements PokeAPIService{
private final PokeAPIRepository repository;
private final PokeAPIExternalClient client;
private final Mapper mapper;

  @Override
  public Mono<PokemonResponse> save(PokemonRequest request) {
    return null;
  }

  @Override
  public Mono<PokemonResponse> register(Integer dexNumber) {
    return Mono.defer(() -> {
      log.info("Search pokemon by Dex number - {}", dexNumber);
        return client.getByDexNumber(dexNumber).map(mapper::DTOToEntity);
  }).flatMap(repository::save).map(mapper::EntityToResponse)
      .switchIfEmpty(Mono.error(new Exception())) //EXCEPTION
      .subscribeOn(Schedulers.boundedElastic());
  }

  @Override
  public Flux<PokemonResponse> getAll() {
    return Flux.defer(() -> {
      log.info("Getting all pokemons");
      return repository.findAll().map(mapper::EntityToResponse);
    }).subscribeOn(Schedulers.boundedElastic());
  }

  @Override
  public Flux<PokemonResponse> getByDexNumber(Integer dexNumber) {
    return Flux.defer(() ->{
      log.info("Getting all pokemons by dex number - {}", dexNumber);
      return repository.findAllByDexNumberAndIsAvailableTrue(dexNumber).map(mapper::EntityToResponse);
        }).subscribeOn(Schedulers.boundedElastic());
  }

  @Override
  public Flux<PokemonResponse> searchByName(String name) {
    return Flux.defer(() ->{
      log.info("Searching all pokemons by name - {}", name);
      return repository.findAllByNameStartingWithAndIsAvailableTrue(name).map(mapper::EntityToResponse);
    }).subscribeOn(Schedulers.boundedElastic());
  }

  @Override
  public Mono<PokemonResponse> update(PokemonRequest request, String id) {
    return null;
  }

  @Override
  public Mono<PokemonResponse> getById(String id) {
    return Mono.defer(() -> {
      log.info("Getting pokemon by register ID - {}", id);
      return repository.findById(id).map(mapper::EntityToResponse);
    }).switchIfEmpty(Mono.error(new Exception())) //EXCEPTION
      .subscribeOn(Schedulers.boundedElastic());
  }

  @Override
  public Mono<Void> deleteById(String id) {
    return Mono.defer(() -> {
        log.info("Deleting pokemon by register ID - {}", id);
        return repository.existsById(id)
          .flatMap(exists -> {
          if(exists) {
            return repository.findById(id).flatMap(pokemon -> {
              pokemon.changeAvailability();
              return repository.save(pokemon).then();
            });
          }else{ return Mono.error(new Exception());
          }
        });
      }).subscribeOn(Schedulers.boundedElastic());
  }
}
