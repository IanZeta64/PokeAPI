package com.pokeapi.service;

import com.pokeapi.client.PokeAPIExternalClient;
import com.pokeapi.dto.PokemonRequest;
import com.pokeapi.dto.PokemonResponse;
import com.pokeapi.exceptions.*;
import com.pokeapi.repository.PokeAPIRepository;
import com.pokeapi.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import static com.pokeapi.constants.GlobalConstants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class PokeAPIServiceImpl implements PokeAPIService {
  private final PokeAPIRepository repository;
  private final PokeAPIExternalClient client;
  private final Mapper mapper;

  @Override
  public Mono<PokemonResponse> save(PokemonRequest request) {
    return Mono.defer(() -> {
      log.info("Saving a new pokemon - {}", request.fullName());
      return repository.existsByDexNumber(request.dexNumber())
        .flatMap(existsByDex -> {
          if (Boolean.FALSE.equals(existsByDex)) {
            return client.getByDexNumber(request.dexNumber())
              .flatMap(dto -> repository.save(mapper.DTOToEntity(dto)))//REGISTER ORIGINAL PKM
              .flatMap(savedDTO -> repository.save(mapper.RequestToEntity(request)).map(mapper::EntityToResponse)); //SAVE NEW REGIONAL PKM
          }
          log.info("Checking duplicated pokemon - {}", request.fullName());
          return repository.existsByNameAndIsAvailableTrue(request.fullName())
            .flatMap(existsByName -> {
              if (Boolean.TRUE.equals(existsByName)) {
                return Mono.error(new DuplicatedPokemonException(String.format(DUPLICATED_POKEMON_EXCEPTION_MSG, NAME, request.fullName()))); //EXCEPTION
              }
              return repository.save(mapper.RequestToEntity(request)).map(mapper::EntityToResponse); //SAVE A NEW FKM
            });
        });
//        .switchIfEmpty(Mono.error(new PokemonNotCreatedException(String.format(POKEMON_NOT_CREATED_EXCEPTION_MSG, request.fullName())))); //EXCEPTION
    }).subscribeOn(Schedulers.boundedElastic());
  }

  @Override
  public Mono<PokemonResponse> registerByDexNumber(Integer dexNumber) {
    return Mono.defer(() -> {
      log.info("Search pokemon by Dex number - {}", dexNumber);
      return repository.existsByDexNumber(dexNumber)
        .flatMap(exists -> {
          if (Boolean.TRUE.equals(exists)) {
            return repository.findAllByDexNumberAndIsAvailableTrue(dexNumber)
              .next().map(mapper::EntityToResponse);
          }
          return client.getByDexNumber(dexNumber).map(mapper::DTOToEntity)
            .flatMap(repository::save).map(mapper::EntityToResponse);

        }).switchIfEmpty(Mono.error(new PokemonNotFoundException(
          String.format(POKEMON_NOT_FOUND_EXCEPTION_MSG, DEX_NUMBER, dexNumber))));//EXCEPTION;
    }).subscribeOn(Schedulers.boundedElastic());
  }

  @Override
  public Mono<PokemonResponse> registerByNamer(String id) {
    return null;
  }

  @Override
  public Flux<PokemonResponse> getAll() {
    return Flux.defer(() -> {
      log.info("Getting all pokemons");
      return repository.findByIsAvailableTrue().map(mapper::EntityToResponse);
    }).subscribeOn(Schedulers.boundedElastic());
  }

  @Override
  public Flux<PokemonResponse> getAllFake() {
    return Flux.defer(() -> {
      log.info("Getting all created pokemons");
      return repository.findByIsAvailableTrueAndIsFakeTrue().map(mapper::EntityToResponse);
    }).subscribeOn(Schedulers.boundedElastic());
  }

  @Override
  public Flux<PokemonResponse> getByDexNumber(Integer dexNumber) {
    return Flux.defer(() -> {
      log.info("Getting all pokemons by dex number - {}", dexNumber);
      return repository.findAllByDexNumberAndIsAvailableTrue(dexNumber).map(mapper::EntityToResponse);
    }).subscribeOn(Schedulers.boundedElastic());
  }

  @Override
  public Flux<PokemonResponse> searchByName(String name) {
    return Flux.defer(() -> {
      log.info("Searching all pokemons by name - {}", name);
      return repository.findAllByNameStartingWithAndIsAvailableTrue(name).map(mapper::EntityToResponse);
    }).subscribeOn(Schedulers.boundedElastic());
  }

  @Override
  public Mono<PokemonResponse> update(PokemonRequest request, String id) {
    return Mono.defer(() -> {
      log.info("Updating pokemon by id - {}", id);
      return repository.existsByIdAndIsAvailableTrue(id)
        .flatMap(existsById -> {
          if (Boolean.FALSE.equals(existsById)){
            return Mono.error(new PokemonNotFoundException(String.format(POKEMON_NOT_FOUND_EXCEPTION_MSG,ID, id)));//EXCEPTION
          }
          return repository.findById(id)
            .flatMap(pokemon -> repository.save(pokemon.update(request)))
            .map(mapper::EntityToResponse);
        });
//        .switchIfEmpty(Mono.error(new PokemonNotUpdatedException(String.format(POKEMON_NOT_UPDATED_EXCEPTION_MSG, request.fullName()))));//EXCEPTION
    }).subscribeOn(Schedulers.boundedElastic());
  }

  @Override
  public Mono<PokemonResponse> getById(String id) {
    return Mono.defer(() -> {
        log.info("Getting pokemon by register ID - {}", id);
        return repository.findByIdAndIsAvailableTrue(id).map(mapper::EntityToResponse);
      }).switchIfEmpty(Mono.error(new PokemonNotFoundException(String.format(POKEMON_NOT_FOUND_EXCEPTION_MSG, ID, id)))) //EXCEPTION
      .subscribeOn(Schedulers.boundedElastic());
  }

  @Override
  public Mono<Void> deleteById(String id) {
    return Mono.defer(() -> {
      log.info("Deleting pokemon by register ID - {}", id);
      return repository.existsById(id)
        .flatMap(exists -> {
          if (Boolean.FALSE.equals(exists)) {
            return Mono.error(new PokemonNotFoundException(String.format(POKEMON_NOT_FOUND_EXCEPTION_MSG, ID, id))); //EXCEPTION
          }
          return repository.findByIdAndIsAvailableTrueAndIsFakeTrue(id).flatMap(pokemon -> {
            pokemon.changeAvailability();
            return repository.save(pokemon).then();
        });
        });
//        .switchIfEmpty(Mono.error(new PokemonNotDeletedException(String.format(POKEMON_NOT_DELETED_EXCEPTION_MSG, id))));
    }).subscribeOn(Schedulers.boundedElastic());
  }
}
