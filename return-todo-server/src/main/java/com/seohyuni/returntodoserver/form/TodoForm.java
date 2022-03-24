package com.seohyuni.returntodoserver.form;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

public class TodoForm {

  public static class Input {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "Todo 완료여부")
      private Boolean complete;

      @ApiModelProperty(value = "태그 식별번호")
      private Long tagId;

    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Add {

      @ApiModelProperty(value = "Todo 내용", required = true)
      @NotBlank(message = "'content' must not be null")
      private String content;

      @ApiModelProperty(value = "태그 식별번호")
      private Long tagId;

    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {

      @ApiModelProperty(value = "Todo 내용", required = true)
      @NotBlank(message = "'content' must not be null")
      private String content;

      @ApiModelProperty(value = "태그 식별번호")
      private Long tagId;

    }
  }

  public static class Output {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "Todo 식별번호")
      private Long id;

      @ApiModelProperty(value = "Todo 내용")
      private String content;

      @ApiModelProperty(value = "Todo 완료여부")
      private Boolean complete;

      @ApiModelProperty(value = "Todo 생성일시")
      private LocalDateTime createdAt;

      @ApiModelProperty(value = "태그 식별번호")
      private Long tagId;

      @ApiModelProperty(value = "태그 이름")
      private String tagName;

      @ApiModelProperty(value = "태그 색상코드")
      private String tagColorCode;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get {

      @ApiModelProperty(value = "Todo 식별번호")
      private Long id;

      @ApiModelProperty(value = "Todo 내용")
      private String content;

      @ApiModelProperty(value = "Todo 완료여부")
      private Boolean complete;

      @ApiModelProperty(value = "Todo 생성일시")
      private LocalDateTime createdAt;

      @ApiModelProperty(value = "태그 식별번호")
      private Long tagId;

      @ApiModelProperty(value = "태그 이름")
      private String tagName;

      @ApiModelProperty(value = "태그 색상코드")
      private String tagColorCode;
    }

  }
}
