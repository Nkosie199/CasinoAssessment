package com.assessment.casino.web.error;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BadRequestExceptionHandler {

  @org.springframework.web.bind.annotation.ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  Map<String, String> showCustomMessage(BadRequestException e) {
    Map<String, String> response = new HashMap<>();
    response.put("error", "Bad request");
    response.put("status", "400");
    response.put("message", "Your input is invalid");

    return response;
  }
}
