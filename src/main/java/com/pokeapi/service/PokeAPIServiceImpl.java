package com.pokeapi.service;

//import com.pokeapi.client.PokeAPIExternalClient;
import com.pokeapi.client.PokeAPIExternalClient;
import com.pokeapi.client.dto.PokeDTO;
import com.pokeapi.dto.PokemonRequest;
import com.pokeapi.dto.PokemonResponse;
import com.pokeapi.repository.PokeAPIRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
@Slf4j
public class PokeAPIServiceImpl implements PokeAPIService{
private final PokeAPIRepository repository;
private final PokeAPIExternalClient client;

  @Override
  public Mono<PokemonResponse> save(PokemonRequest request) {
    return null;
  }

  @Override
  public Flux<PokemonResponse> getAll() {
    return null;
  }

  @Override
  public Flux<PokeDTO> getByDexNumber(Integer dexNumber) {
    return Flux.defer(() -> {
      return client.searchByDexNumber(dexNumber);
    }).subscribeOn(Schedulers.boundedElastic());
  }

  @Override
  public Flux<PokemonResponse> getByName(Integer dexNumber) {
    return null;
  }

  @Override
  public Mono<PokemonResponse> update(PokemonRequest request, Integer id) {
    return null;
  }

  @Override
  public Mono<PokemonResponse> getById(Integer id) {
    return null;
  }

  @Override
  public Mono<Void> deleteById(Integer id) {
    return null;
  }
}
