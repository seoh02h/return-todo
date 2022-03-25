package com.seohyuni.returntodoserver.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class ErrorForm {

  public static class Output {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Message {

      @ApiModelProperty(value = "에러 코드")
      private String code;

      @ApiModelProperty(value = "에러 메시지")
      private String message;

    }
  }
}
