package com.seohyuni.returntodoserver.controller;

import com.seohyuni.returntodoserver.form.TagForm;
import com.seohyuni.returntodoserver.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@Api(value = "Tag", tags = {"Tag"})
@RequiredArgsConstructor
public class TagController {

  private final TagService service;

  @SneakyThrows
  @ApiOperation("태그 목록 조회")
  @GetMapping("/tag")
  public List<TagForm.Output.GetAll> getList() {
    return service.getList();
  }

  @SneakyThrows
  @ApiOperation("태그 조회")
  @GetMapping("/tag/{id}")
  public TagForm.Output.Get get(@PathVariable Long id) {
    return service.get(id);
  }

  @SneakyThrows
  @ApiOperation("태그 생성")
  @PostMapping("/tag/{id}")
  public TagForm.Output.Get add(@RequestBody TagForm.Input.Add in) {
    return service.add(in);
  }

  @SneakyThrows
  @ApiOperation("태그 수정")
  @PutMapping("/tag/{id}")
  public TagForm.Output.Get update(@PathVariable Long id, @RequestBody TagForm.Input.Update in) {
    return service.update(id, in);
  }

  @SneakyThrows
  @ApiOperation("태그 삭제")
  @DeleteMapping("/tag/{id}")
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }


}
