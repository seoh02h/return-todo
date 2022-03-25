package com.seohyuni.returntodoserver.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.seohyuni.returntodoserver.form.TodoForm;
import com.seohyuni.returntodoserver.model.QTodo;

public class TodoFormPredicate {

  public static Predicate search(TodoForm.Input.GetAll in) {
    BooleanBuilder builder = new BooleanBuilder();
    QTodo qTodo = QTodo.todo;

    if (in.getComplete() != null) {
      builder.and(qTodo.complete.eq(in.getComplete()));
    }

    if (in.getTagId() != null) {
      builder.and(qTodo.tag().id.eq(in.getTagId()));
    }

    return builder;
  }

}
