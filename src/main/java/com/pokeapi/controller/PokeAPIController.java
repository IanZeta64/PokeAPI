package com.pokeapi.controller;

import com.pokeapi.dto.PokemonRequest;
import com.pokeapi.dto.PokemonResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/PokeAPI")
public interface PokeAPIController {
  @PostMapping
  PokemonResponse save(@Validated @RequestBody PokemonRequest request);
  @GetMapping
  List<PokemonResponse> getAll();
  @GetMapping("/{dexNumber}")
  List<PokemonResponse> getByDexNumber(@PathVariable Integer dexNumber);
  @GetMapping(params = "name")
  List<PokemonResponse> getByName(@RequestParam Integer dexNumber);
  @PutMapping("/{id}")
  PokemonResponse update(@Validated @RequestBody PokemonRequest request, @PathVariable Integer id);
  @GetMapping("/{id}")
  PokemonResponse getById(@PathVariable Integer id);
  @DeleteMapping("/{id}")
  void deleteById(@PathVariable Integer id);
}
