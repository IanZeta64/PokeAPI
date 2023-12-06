package com.pokeapi.util;

import com.pokeapi.client.dto.PokeDTO;
import com.pokeapi.domain.Ability;
import com.pokeapi.domain.Move;
import com.pokeapi.domain.Stat;
import com.pokeapi.domain.Type;
import com.pokeapi.dto.PokemonRequest;
import com.pokeapi.dto.PokemonResponse;
import com.pokeapi.model.Pokemon;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
public class Mapper {

  public Pokemon DTOToEntity(PokeDTO dto){
    List<Ability> abilities = new ArrayList<>();
    dto.getAbilities().forEach(ablDTO ->
      abilities.add(new Ability(ablDTO.slot(), ablDTO.abilityDTO().name(), ablDTO.hidden())));

    List<Type> types = new ArrayList<>();
    dto.getTypes().forEach(typeInfoDTO ->
      types.add( new Type(typeInfoDTO.slot(), typeInfoDTO.type().name())));

    List<Stat> stats = new ArrayList<>();
    dto.getStats().forEach(statsInfoDTO ->
      stats.add(new Stat(statsInfoDTO.baseStat(), statsInfoDTO.statDTO().name())));

    List<Move> moves = new ArrayList<>();
    dto.getMoves().forEach(moveInfoDTO ->
      moves.add(new Move(moveInfoDTO.moveDTO().name())));

    List<String> imagesUrl = new ArrayList<>();
    imagesUrl.add(dto.getImagesUrl().otherDTO().officialArtworkDTO().imageDefaultUrl());
    imagesUrl.add(dto.getImagesUrl().otherDTO().officialArtworkDTO().ImageShinyUrl());

    return new Pokemon(dto.getDexNumber(), dto.getName(), dto.getHeight(), dto.getWeight(), dto.getBaseExp(),
      abilities, types, stats, moves, imagesUrl, Instant.now(), null, false, true);
  }
  public Pokemon RequestToEntity(PokemonRequest request){
    return new Pokemon(request.dexNumber(), request.name().concat("-").concat(request.region()), request.height(), request.weight(), request.baseExp(),
      request.abilities(), request.types(), request.stats(), request.moves(), request.spritesUrl(), Instant.now(), null, true, true);
  }
  public PokemonResponse EntityToResponse(Pokemon entity){
    return new PokemonResponse(entity.getId(), entity.getDexNumber(), entity.getName(), entity.getHeight(), entity.getWeight(),
      entity.getBaseExp(), entity.getAbilities(), entity.getTypes(), entity.getStats() ,entity.getMoves(), entity.getImages());
  }
}
