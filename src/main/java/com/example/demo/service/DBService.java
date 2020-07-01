package com.example.demo.service;


import com.example.demo.domain.Todo;
/*import com.example.demo.mapper.TodoMapper;*/
import com.example.demo.domain.USER_MST_PP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao1.DB1Mapper;
import com.example.demo.dao2.DB2Mapper;

import java.util.Date;
import java.util.List;


@Service
@Transactional
public class DBService {


    @Autowired
    private DB2Mapper db2Mapper;

    @Autowired
    private DB1Mapper db1Mapper;

    /* select dual */
    public String getDb1Dual() throws Exception{
        return db1Mapper.getDb1Dual();
    }


    /* select dual */
    public String getDb2Dual() throws Exception{
        return db2Mapper.getDb2Dual();
    }

    public List<Todo> getTodos() {
        return db2Mapper.selectTodo();
    }

    public void removeTodo(String id) {
        db2Mapper.deleteTodo(id);
    }

    public Todo saveTodo(Todo todo) {
        todo.setUpdateAt(new Date());
        db2Mapper.updateTodo(todo);
        return todo;
    }

    public Todo addTodo(Todo todo) {
        //Number aLong = 101;
        //todo.setId(aLong);
        todo.setCreateAt(new Date());
        todo.setUpdateAt(new Date());
        db2Mapper.insertTodo(todo);
        return todo;
    }

    public List<USER_MST_PP> selectUser() {
        return db2Mapper.selectUser();
    }

    public void deleteUSer(String user_id) {
        db2Mapper.deleteUser(user_id);
    }

    public USER_MST_PP updateUser(USER_MST_PP ump) {
        ump.setLast_update_dtts(new Date());
        db2Mapper.updateUser(ump);
        return ump;
    }

    public USER_MST_PP insertUser(USER_MST_PP ump) {
        ump.setCreate_dtts(new Date());
        db2Mapper.insertUser(ump);
        return ump;
    }

}