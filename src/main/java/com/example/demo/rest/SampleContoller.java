package com.example.demo.rest;

import com.example.demo.component.PublishCom;
import com.example.demo.component.SubscribeCom;
import com.example.demo.domain.Todo;
import com.example.demo.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.jms.JMSException;
import java.util.List;

@RestController
@RequestMapping("/sample/todos")
@Slf4j
public class SampleContoller {
    @Autowired
    private TodoService service;

    @Autowired
    private PublishCom publishCom;

    @Autowired
    private SubscribeCom subscribeCom;

    @GetMapping
    public List<Todo> todos() {
        return service.getTodos();
    }

    @DeleteMapping("/{id}")
    public void rmTodo(@PathVariable Long id) {
        service.removeTodo(id);
    }

    @PutMapping
    public Todo addTodo(@RequestBody Todo todo) {
        return service.addTodo(todo);
    }

    @PostMapping
    public Todo saveTodo(@RequestBody Todo todo) {
        return service.saveTodo(todo);
    }

    @PostMapping("/sendwait")
    public Todo sendwait(@RequestBody Todo todo) throws JMSException {
        return publishCom.sendWait(todo);
    }
}
