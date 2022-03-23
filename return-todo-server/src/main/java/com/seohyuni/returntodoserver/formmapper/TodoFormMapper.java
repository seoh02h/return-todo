package com.seohyuni.returntodoserver.formmapper;

import com.seohyuni.returntodoserver.form.TodoForm;
import com.seohyuni.returntodoserver.model.Todo;
import java.util.List;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(
    implementationName = "TodoFormMapperImpl",
    builder = @Builder(disableBuilder = true)
)
public abstract class TodoFormMapper {

  public abstract List<TodoForm.Output.GetAll> toGetAllList(List<Todo> in);

  public abstract Todo toTodo(TodoForm.Input.Add in);

  public abstract TodoForm.Output.Get toGet(Todo in);
}
