package com.pokeapi.dto;

import com.pokeapi.domain.Ability;
import com.pokeapi.domain.Move;
import com.pokeapi.domain.Stat;
import com.pokeapi.domain.Type;

import java.util.List;

public record PokemonRequest(Integer dexNumber, String name, String region, Float height, Float weight,
                             Integer baseExp, List<Ability> abilities, List<Type> types,
                             List<Stat> stats, List<Move> moves, List<String> spritesUrl) {
}

/*
dex
name
height
weight
types
stats
moves
sprites
 */

