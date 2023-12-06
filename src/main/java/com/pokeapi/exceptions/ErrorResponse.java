package com.pokeapi.exceptions;

import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
public class ErrorResponse {
  private int status;
  private String error;
  private List<String> messages;
  private Instant instant;

  public ErrorResponse(int status, String error, List<String> messageList) {
    this.status = status;
    this.error = error;
    this.messages = messageList;
    this.instant = Instant.now();
  }
}
