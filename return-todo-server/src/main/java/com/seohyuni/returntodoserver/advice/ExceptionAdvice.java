package com.seohyuni.returntodoserver.advice;

import com.seohyuni.returntodoserver.form.ErrorForm;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorForm.Output.Message defaultException(Exception e) {
    return ErrorForm.Output.Message.builder()
        .code("500")
        .message(e.getMessage())
        .build();
  }


}
