package com.example.demo.dao2;

import com.example.demo.domain.Todo;
import com.example.demo.domain.USER_MST_PP;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper()
public interface DB2Mapper {

    public default String getDb2Dual() throws Exception {
        return null;
    }

    List<Todo> selectTodo();

    void deleteTodo(String id);

    void insertTodo(Todo todo);

    void updateTodo(Todo todo);


    List<USER_MST_PP> selectUser();

    void deleteUser(String user_id);

    void insertUser(USER_MST_PP ump);

    void updateUser(USER_MST_PP ump);
}
