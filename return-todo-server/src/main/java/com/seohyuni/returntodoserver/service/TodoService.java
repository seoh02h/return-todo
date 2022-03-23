package com.seohyuni.returntodoserver.service;

import com.querydsl.core.types.Predicate;
import com.seohyuni.returntodoserver.form.TodoForm;
import com.seohyuni.returntodoserver.formmapper.TodoFormMapper;
import com.seohyuni.returntodoserver.mapper.TodoMapper;
import com.seohyuni.returntodoserver.model.Todo;
import com.seohyuni.returntodoserver.repository.TagRepository;
import com.seohyuni.returntodoserver.repository.TodoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TodoService {

  private final TodoRepository repository;

  private final TagRepository tagRepository;

  private final TodoFormMapper formMapper;

  private final TodoMapper mapper;

  @Transactional(readOnly = true)
  public List<TodoForm.Output.GetAll> getList(Predicate search) {
    return formMapper.toGetAllList(
        (List<Todo>) repository.findAll(search, Sort.by(Direction.DESC, "createdAt")));
  }

  @Transactional(readOnly = true)
  public Page<TodoForm.Output.GetAll> getPage(Predicate search, Pageable pageable) {
    return repository.findAll(search, pageable).map(formMapper::toGetAll);
  }

  @Transactional(readOnly = true)
  public TodoForm.Output.Get get(Long id) {
    return formMapper.toGet(repository.getById(id));
  }

  @Transactional
  public TodoForm.Output.Get add(TodoForm.Input.Add in) {
    Todo todo = formMapper.toTodo(in);
    todo.setTag(tagRepository.getById(in.getTagId()));
    return formMapper.toGet(repository.save(todo));
  }

  @Transactional
  public TodoForm.Output.Get update(Long id, TodoForm.Input.Update in) {
    Todo todo = mapper.update(in, repository.getById(id));
    todo.setTag(tagRepository.getById(in.getTagId()));
    return formMapper.toGet(todo);
  }

  @Transactional
  public void delete(Long id) {
    repository.deleteById(id);
  }

  @Transactional
  public TodoForm.Output.Get toggleComplete(Long id) {
    Todo todo = repository.getById(id);
    todo.toggleComplete();
    return formMapper.toGet(todo);
  }

}
