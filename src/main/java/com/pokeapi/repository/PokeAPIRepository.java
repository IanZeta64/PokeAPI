package com.pokeapi.repository;

import com.pokeapi.model.Pokemon;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PokeAPIRepository extends ReactiveMongoRepository<Pokemon, String> {
}
