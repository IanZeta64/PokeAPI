package com.pokeapi.repository;

import com.pokeapi.model.Pokemon;
import org.reactivestreams.Publisher;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface PokeAPIRepository extends ReactiveMongoRepository<Pokemon, String> {

  Flux<Pokemon> findAllByDexNumber(Integer dexNumber);
}
