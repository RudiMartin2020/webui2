package com.example.demo.mapper;

import com.example.demo.domain.Todo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoMapper {
    List<Todo> selectTodos();

    void deleteTodo(Long id);

    void insertTodo(Todo todo);

    void updateTodo(Todo todo);
}
