package com.pokeapi.client;

import com.pokeapi.client.dto.PokeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
public class PokeAPIExternalClient {
  private static final String POKE_API_EXTERNAL_URL = "https://pokeapi.co/api/v2/pokemon/";
  private final WebClient client;
//  private final ReactiveCircuitBreaker reactiveCircuitBreaker;

  public PokeAPIExternalClient(WebClient.Builder builder
//    , ReactiveCircuitBreakerFactory<?,?> reactiveCircuitBreaker
  ) {
    this.client = builder.baseUrl(POKE_API_EXTERNAL_URL).build();
//    this.reactiveCircuitBreaker = reactiveCircuitBreaker.create("pokeAPI-circuit-breaker");
  }

  public Mono<PokeDTO> searchByDexNumber(Integer dexNumber){
    return this.client.get().uri(dexNumber.toString())
      .exchangeToMono(result -> {
        if (result.statusCode().is2xxSuccessful()){
          return result.bodyToMono(PokeDTO.class);
        }else {
          return Mono.empty();
        }
      }).subscribeOn((Schedulers.boundedElastic()));
  }
}
