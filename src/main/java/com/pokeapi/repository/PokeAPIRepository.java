package com.pokeapi.repository;

import com.pokeapi.model.Pokemon;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface PokeAPIRepository extends ReactiveMongoRepository<Pokemon, String> {

  Flux<Pokemon> findAllByDexNumberAndIsAvailableTrue(Integer dexNumber);
  Flux<Pokemon> findAllByNameAndIsAvailableTrue(String name);
  Flux<Pokemon> findAllByNameStartingWithAndIsAvailableTrue(String name);

  Mono<Boolean> existsByDexNumberAndIsAvailableTrue(Integer dexNumber);

  Flux<Pokemon> findByIsAvailableTrue();
  Flux<Pokemon> findByIsAvailableTrueAndIsFakeTrue();

  Mono<Pokemon> findByIdAndIsAvailableTrue(String id);
  Mono<Pokemon> findByIdAndIsAvailableTrueAndIsFakeTrue(String id);


  Mono<Boolean> existsByIdAndIsAvailableTrue(String id);
  Mono<Boolean> existsByIdAndIsAvailableTrueAndIsFakeTrue(String id);
  Mono<Boolean> existsByNameAndIsAvailableTrue(String name);

  Mono<Boolean> existsByDexNumber(Integer integer);

  Mono<Boolean> existsByName(String name);
}
