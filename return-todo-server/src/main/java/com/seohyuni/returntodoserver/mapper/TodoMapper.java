package com.seohyuni.returntodoserver.mapper;

import com.seohyuni.returntodoserver.form.TodoForm;
import com.seohyuni.returntodoserver.model.Todo;
import org.mapstruct.Mapper;

@Mapper
public abstract class TodoMapper {

  public Todo update(TodoForm.Input.Update in, Todo out) {
    if (in == null) {
      return null;
    }
    out.setContent(in.getContent());
    return out;
  }
}
