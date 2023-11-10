package com.pokeapi.controller;

import com.pokeapi.dto.PokemonRequest;
import com.pokeapi.dto.PokemonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;


@RequestMapping("/PokeAPI")
public interface PokeAPIController {
  @PostMapping
  Mono<ResponseEntity<PokemonResponse>> save(@Validated @RequestBody PokemonRequest request);
  @PostMapping("/{dexNumber}")
  Mono<ResponseEntity<PokemonResponse>> register(@Validated @PathVariable Integer dexNumber);
  @GetMapping
  Mono<ResponseEntity<List<PokemonResponse>>> getAll();
  @GetMapping("/dex/{dexNumber}")
  Mono<ResponseEntity<List<PokemonResponse>>> getByDexNumber(@PathVariable Integer dexNumber);
  @GetMapping(params="name")
  Mono<ResponseEntity<List<PokemonResponse>>> searchByName(@RequestParam String name);
  @PutMapping("/{id}")
  Mono<ResponseEntity<PokemonResponse>> update(@Validated @RequestBody PokemonRequest request, @PathVariable String id);
  @GetMapping("/id/{id}")
  Mono<ResponseEntity<PokemonResponse>> getById(@PathVariable String id);
  @DeleteMapping("/{id}")
  Mono<ResponseEntity<Void>> deleteById(@PathVariable String id);
}
