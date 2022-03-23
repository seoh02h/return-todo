package com.seohyuni.returntodoserver.controller;

import com.seohyuni.returntodoserver.form.TodoForm;
import com.seohyuni.returntodoserver.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@Api(value = "Todo", tags = {"Todo"})
@RequiredArgsConstructor
public class TodoController {

  private final TodoService service;

  @SneakyThrows
  @ApiOperation("Todo 목록 조회")
  @GetMapping("/todo")
  public List<TodoForm.Output.GetAll> getList(TodoForm.Input.GetAll in) {
    return service.getList();
  }

  @SneakyThrows
  @ApiOperation("Todo 생성")
  @PostMapping("/todo")
  public TodoForm.Output.Get add(@RequestBody TodoForm.Input.Add in) {
    return service.add(in);
  }

}
