package com.example.demo.service;

import com.example.demo.domain.Todo;
import com.example.demo.mapper.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TodoService {
    @Autowired
    private TodoMapper mapper;

    public List<Todo> getTodos() {
        return mapper.selectTodos();
    }

    public void removeTodo(Long id) {
        mapper.deleteTodo(id);
    }

    public Todo saveTodo(Todo todo) {
        todo.setUpdateAt(new Date());
        mapper.updateTodo(todo);
        return todo;
    }

    public Todo addTodo(Todo todo) {
        todo.setCreateAt(new Date());
        mapper.insertTodo(todo);
        return todo;
    }
}
