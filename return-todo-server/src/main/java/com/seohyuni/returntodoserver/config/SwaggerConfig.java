package com.seohyuni.returntodoserver.config;

import com.fasterxml.classmate.TypeResolver;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket api() {
    TypeResolver typeResolver = new TypeResolver();

    Docket docket = new Docket(DocumentationType.SWAGGER_2);
    docket.apiInfo(apiInfo())
        .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(Pageable.class),
            typeResolver.resolve(Page.class)))
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.ant("/api/**"))
        .build();

    int tagOrd = 0;
    docket.tags(
        new Tag("Todo", "Todo API", ++tagOrd),
        new Tag("Tag", "태그 API", ++tagOrd)

    );
    return docket;
  }

  private static final String INFO_TITLE = "Return Todo REST API";
  private static final String INFO_VERSION = "0.0.1";
  private static final String INFO_DESC = "Return Todo API Specification";


  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title(INFO_TITLE)
        .version(INFO_VERSION)
        .description(INFO_DESC)
        .build();
  }

  @Bean
  public UiConfiguration uiConfiguration() {
    return UiConfigurationBuilder.builder()
        .defaultModelRendering(ModelRendering.MODEL)
        .build();
  }

  @Data
  @ApiModel
  static class Page {

    @ApiModelProperty(value = "페이지 번호(0..N)")
    private Integer page;

    @ApiModelProperty(value = "페이지 크기", allowableValues = "range[0, 100]")
    private Integer size;

    @ApiModelProperty(value = "정렬(사용법: 컬럼명,ASC|DESC)")
    private List<String> sort;
  }
}
