package com.pokeapi.service;

import com.pokeapi.client.PokeAPIExternalClient;
import com.pokeapi.dto.PokemonRequest;
import com.pokeapi.dto.PokemonResponse;
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
public class PokeAPIServiceImpl implements PokeAPIService {
  private final PokeAPIRepository repository;
  private final PokeAPIExternalClient client;
  private final Mapper mapper;

  @Override
  public Mono<PokemonResponse> save(PokemonRequest request) {
    return Mono.defer(() -> {
      log.info("Saving a new pokemon - {}", request.name().concat("-").concat(request.region()));
      return repository.existsByDexNumberAndIsAvailableTrue(request.dexNumber())
        .flatMap(existsByDex -> {
          if (!existsByDex) {
            return client.getByDexNumber(request.dexNumber())
              .flatMap(dto -> repository.save(mapper.DTOToEntity(dto)))
              .flatMap(savedDTO -> repository.save(mapper.RequestToEntity(request)).map(mapper::EntityToResponse));
          }
          log.info("Checking duplicated pokemon - {}", request.name().concat("-").concat(request.region()));
          return repository.existsByNameAndIsAvailableTrue(request.name().concat("-").concat(request.region()))
            .flatMap(existsByName -> {
              if (existsByName) {
                return Mono.error(new Exception()); //EXCEPTION
              }
              return repository.save(mapper.RequestToEntity(request)).map(mapper::EntityToResponse);
            });
        })
        .switchIfEmpty(Mono.error(new Exception())); //EXCEPTION
    }).subscribeOn(Schedulers.boundedElastic());
  }

  @Override
  public Mono<PokemonResponse> register(Integer dexNumber) {
    return Mono.defer(() -> {
      log.info("Search pokemon by Dex number - {}", dexNumber);
      return repository.existsByDexNumberAndIsAvailableTrue(dexNumber)
        .flatMap(exists -> {
          if (exists) {
            return repository.findAllByDexNumberAndIsAvailableTrue(dexNumber)
              .next().map(mapper::EntityToResponse)
              .switchIfEmpty(Mono.error(new Exception()));//EXCEPTION
          }
          return client.getByDexNumber(dexNumber).map(mapper::DTOToEntity)
            .flatMap(repository::save).map(mapper::EntityToResponse)
            .switchIfEmpty(Mono.error(new Exception()));//EXCEPTION
        });
    }).subscribeOn(Schedulers.boundedElastic());
  }

  @Override
  public Flux<PokemonResponse> getAll() {
    return Flux.defer(() -> {
      log.info("Getting all pokemons");
      return repository.findByIsAvailableTrue().map(mapper::EntityToResponse);
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
          if (!existsById){
            return Mono.error(new Exception());//EXCEPTION
          }
          return repository.findById(id)
            .flatMap(pokemon -> repository.save(pokemon.update(request)))
            .map(mapper::EntityToResponse);
        }).switchIfEmpty(Mono.error(new Exception()));//EXCEPTION
    }).subscribeOn(Schedulers.boundedElastic());
  }

  @Override
  public Mono<PokemonResponse> getById(String id) {
    return Mono.defer(() -> {
        log.info("Getting pokemon by register ID - {}", id);
        return repository.findByIdAndIsAvailableTrue(id).map(mapper::EntityToResponse);
      }).switchIfEmpty(Mono.error(new Exception())) //EXCEPTION
      .subscribeOn(Schedulers.boundedElastic());
  }

  @Override
  public Mono<Void> deleteById(String id) {
    return Mono.defer(() -> {
      log.info("Deleting pokemon by register ID - {}", id);
      return repository.existsByIdAndIsAvailableTrue(id)
        .flatMap(exists -> {
          if (exists) {
            return repository.findByIdAndIsAvailableTrue(id).flatMap(pokemon -> {
              pokemon.changeAvailability();
              return repository.save(pokemon).then();
            });
          } else {
            return Mono.error(new Exception()); //EXCEPTION
          }
        });
    }).subscribeOn(Schedulers.boundedElastic());
  }
}
