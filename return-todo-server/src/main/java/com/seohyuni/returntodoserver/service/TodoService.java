package com.seohyuni.returntodoserver.service;

import com.seohyuni.returntodoserver.form.TodoForm;
import com.seohyuni.returntodoserver.formmapper.TodoFormMapper;
import com.seohyuni.returntodoserver.model.Todo;
import com.seohyuni.returntodoserver.repository.TagRepository;
import com.seohyuni.returntodoserver.repository.TodoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TodoService {

  private final TodoRepository repository;

  private final TagRepository tagRepository;

  private final TodoFormMapper formMapper;

  @Transactional(readOnly = true)
  public List<TodoForm.Output.GetAll> getList() {
    return formMapper.toGetAllList(repository.findAll());
  }

  @Transactional
  public TodoForm.Output.Get add(TodoForm.Input.Add in) {
    Todo todo = formMapper.toTodo(in);
    todo.setTag(tagRepository.getById(in.getTagId()));
    return formMapper.toGet(repository.save(todo));
  }

}
