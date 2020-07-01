package com.example.demo.dao1;

import com.example.demo.domain.Todo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DB1Mapper {

    public default String getDb1Dual() throws Exception {
        return null;
    }

    List<Todo> selectTodo();
    void deleteTodo(Long id);
    void insertTodo(Todo todo);
    void updateTodo(Todo todo);
}

