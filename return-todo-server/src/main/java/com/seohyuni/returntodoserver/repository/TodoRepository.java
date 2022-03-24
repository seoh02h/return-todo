package com.seohyuni.returntodoserver.repository;

import com.seohyuni.returntodoserver.model.Todo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>, QuerydslPredicateExecutor<Todo> {

  List<Todo> findByTag_Id(Long id);

}
