package com.seohyuni.returntodoserver.service;

import com.seohyuni.returntodoserver.form.TagForm;
import com.seohyuni.returntodoserver.formmapper.TagFormMapper;
import com.seohyuni.returntodoserver.mapper.TagMapper;
import com.seohyuni.returntodoserver.model.Tag;
import com.seohyuni.returntodoserver.model.Todo;
import com.seohyuni.returntodoserver.repository.TagRepository;
import com.seohyuni.returntodoserver.repository.TodoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TagService {

  private final TagRepository repository;

  private final TagMapper mapper;

  private final TagFormMapper formMapper;

  private final TodoRepository todoRepository;

  @Transactional(readOnly = true)
  public List<TagForm.Output.GetAll> getList() {
    return formMapper.toGetAllList((List<Tag>) repository.findAll());
  }

  @Transactional(readOnly = true)
  public TagForm.Output.Get get(Long id) {
    return formMapper.toGet(repository.getById(id));
  }

  @Transactional
  public TagForm.Output.Get add(TagForm.Input.Add in) {
    return formMapper.toGet(repository.save(formMapper.toTag(in)));
  }

  @Transactional
  public TagForm.Output.Get update(Long id, TagForm.Input.Update in) {
    return formMapper.toGet(mapper.update(in, repository.getById(id)));
  }

  @Transactional
  public void delete(Long id) {
    List<Todo> todoList = todoRepository.findByTag_Id(id);
    for (Todo todo : todoList) {
      todo.setTag(null);
    }
    repository.deleteById(id);
  }


}
