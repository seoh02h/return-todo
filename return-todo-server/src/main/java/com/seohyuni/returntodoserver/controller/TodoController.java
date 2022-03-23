package com.seohyuni.returntodoserver.controller;

import com.seohyuni.returntodoserver.form.TodoForm;
import com.seohyuni.returntodoserver.predicate.TodoFormPredicate;
import com.seohyuni.returntodoserver.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
@Api(value = "Todo", tags = {"Todo"})
@RequiredArgsConstructor
public class TodoController {

  private final TodoService service;

  @SneakyThrows
  @ApiOperation("Todo 목록 조회")
  @GetMapping("/todo")
  public List<TodoForm.Output.GetAll> getList(TodoForm.Input.GetAll in) {
    return service.getList(TodoFormPredicate.search(in));
  }

  @SneakyThrows
  @ApiOperation("Todo 페이징 조회")
  @GetMapping("/todo/page")
  public Page<TodoForm.Output.GetAll> getPage(TodoForm.Input.GetAll in,
      @PageableDefault(size = 10, sort = "createdAt", direction = Direction.DESC) Pageable pageable) {
    return service.getPage(TodoFormPredicate.search(in), pageable);
  }

  @SneakyThrows
  @ApiOperation("Todo 조회")
  @GetMapping("/todo/{id}")
  public TodoForm.Output.Get get(@PathVariable Long id) {
    return service.get(id);
  }

  @SneakyThrows
  @ApiOperation("Todo 생성")
  @PostMapping("/todo")
  public TodoForm.Output.Get add(@RequestBody TodoForm.Input.Add in) {
    return service.add(in);
  }

  @SneakyThrows
  @ApiOperation("Todo 수정")
  @PutMapping("/todo/{id}")
  public TodoForm.Output.Get update(@PathVariable Long id, @RequestBody TodoForm.Input.Update in) {
    return service.update(id, in);
  }

  @SneakyThrows
  @ApiOperation("Todo 삭제")
  @DeleteMapping("/todo/{id}")
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }

  @SneakyThrows
  @ApiOperation("Todo 완료여부 변경")
  @PutMapping("/todo/{id}/complete")
  public TodoForm.Output.Get toggleComplete(@PathVariable Long id) {
    return service.toggleComplete(id);
  }
}
