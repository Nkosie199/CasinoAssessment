package com.assessment.casino.web.error;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TeapotExceptionHandler {

  @org.springframework.web.bind.annotation.ExceptionHandler
  @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
  @ResponseBody
  Map<String, String> showCustomMessage(TeapotException e) {
    Map<String, String> response = new HashMap<>();
    response.put("error", "Teapot");
    response.put("status", "418");
    response.put("message", "Wager greater than current balance");

    return response;
  }
}
