package com.seohyuni.returntodoserver.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

public class TagForm {

  public static class Input {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Add {

      @ApiModelProperty(value = "태그 이름", required = true)
      @NotBlank(message = "'name' must not be null")
      private String name;

      @ApiModelProperty(value = "태그 색상코드", required = true)
      @NotBlank(message = "'colorCode' must not be null")
      private String colorCode;

    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {

      @ApiModelProperty(value = "태그 이름", required = true)
      @NotBlank(message = "'name' must not be null")
      private String name;

      @ApiModelProperty(value = "태그 색상코드", required = true)
      @NotBlank(message = "'name' must not be null")
      private String colorCode;

    }
  }

  public static class Output {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "태그 식별번호")
      private String id;

      @ApiModelProperty(value = "태그 이름")
      private String name;

      @ApiModelProperty(value = "태그 색상코드")
      private String colorCode;

    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get {

      @ApiModelProperty(value = "태그 식별번호", position = 1)
      private String id;

      @ApiModelProperty(value = "태그 이름", position = 2)
      private String name;

      @ApiModelProperty(value = "태그 색상코드", position = 3)
      private String colorCode;

    }

  }


}
