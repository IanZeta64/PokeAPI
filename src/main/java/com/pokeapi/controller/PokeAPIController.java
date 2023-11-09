package com.pokeapi.controller;

import com.pokeapi.dto.PokemonRequest;
import com.pokeapi.dto.PokemonResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RequestMapping("/PokeAPI")
public interface PokeAPIController {
  @PostMapping
  Mono<PokemonResponse> save(@Validated @RequestBody PokemonRequest request);
  @GetMapping
  Flux<PokemonResponse> getAll();
  @GetMapping("/dex/{dexNumber}")
  Flux<PokemonResponse> getByDexNumber(@PathVariable Integer dexNumber);
  @GetMapping(params="name")
  Flux<PokemonResponse> getByName(@RequestParam Integer dexNumber);
  @PutMapping("/{id}")
  Mono<PokemonResponse> update(@Validated @RequestBody PokemonRequest request, @PathVariable String id);
  @GetMapping("/registerId/{id}")
  Mono<PokemonResponse> getById(@PathVariable String id);
  @DeleteMapping("/{id}")
  Mono<Void> deleteById(@PathVariable String id);
}
