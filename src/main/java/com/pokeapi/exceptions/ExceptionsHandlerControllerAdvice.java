package com.pokeapi.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.List;



@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
  public class ExceptionsHandlerControllerAdvice {


  private ResponseEntity<Object> handlePokemonException(HttpStatus status, List<String> errorMessages) {
    ErrorResponse errorResponse = new ErrorResponse(status.value(), status.getReasonPhrase(), errorMessages);
    return new ResponseEntity<>(errorResponse, status);
  }

    @ExceptionHandler(PokemonNotFoundException.class)
    protected ResponseEntity<Object> handlePokemonNotFoundExceptions(PokemonNotFoundException ex) {
      return handlePokemonException(HttpStatus.NOT_FOUND, List.of(ex.getMessage()));
    }
  @ExceptionHandler(PokemonNotCreatedException.class)
  protected ResponseEntity<Object> handlePokemonNotCreatedExceptions(PokemonNotCreatedException ex) {
    return handlePokemonException(HttpStatus.BAD_REQUEST, List.of(ex.getMessage()));
  }

  @ExceptionHandler(PokemonNotUpdatedException.class)
  protected ResponseEntity<Object> handlePokemonNotUpdatedExceptions(PokemonNotUpdatedException ex) {
    return handlePokemonException(HttpStatus.BAD_REQUEST, List.of(ex.getMessage()));
  }

  @ExceptionHandler(PokemonNotDeletedException.class)
  protected ResponseEntity<Object> handlePokemonNotDeletedExceptions(PokemonNotDeletedException ex) {
    return handlePokemonException(HttpStatus.BAD_REQUEST, List.of(ex.getMessage()));
  }

  @ExceptionHandler(DuplicatedPokemonException.class)
  protected ResponseEntity<Object> handleDuplicatedPokemonException(DuplicatedPokemonException ex) {
    return handlePokemonException(HttpStatus.CONFLICT, List.of(ex.getMessage()));
  }

  @ExceptionHandler(WebExchangeBindException.class)
  protected ResponseEntity<Object> handleException(WebExchangeBindException ex) {
    var errors = ex.getBindingResult().getAllErrors().stream()
      .map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
    return handlePokemonException(HttpStatus.BAD_REQUEST, errors);
  }
  }
