package com.example.demo.rest;

import com.example.demo.component.PublishCom;
import com.example.demo.component.SubscribeCom;
import com.example.demo.domain.Todo;
import com.example.demo.domain.USER_MST_PP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.DBService;

import javax.jms.JMSException;
import java.util.List;


@RestController
@RequestMapping("/sample/userinfo")
@Slf4j
public class DBController {

    @Autowired
    private DBService dbService;

    @Autowired
    private PublishCom publishCom;

    @Autowired
    private SubscribeCom subscribeCom;

    @RequestMapping("/")
    public @ResponseBody String root_test() throws Exception{
        return "Hello World";
    }

    @GetMapping("/db2/get")
    public List<Todo> todos() {
        return dbService.getTodos();
    }

    @DeleteMapping("/db2/rmtodo/{id}")
    public void rmTodo(@PathVariable String id) {
        dbService.removeTodo(id);
    }

    @PutMapping("/db2/addtodo")
    public Todo addTodo(@RequestBody Todo todo) {
        return dbService.addTodo(todo);
    }

    @PostMapping("/db2/savetodo")
    public Todo saveTodo(@RequestBody Todo todo) {
        return dbService.saveTodo(todo);
    }

    @RequestMapping("/db2")
    public @ResponseBody String db2() throws Exception{
        return dbService.getDb2Dual();
    }

    @RequestMapping("/db1")
    public @ResponseBody String db1() throws Exception{
        return dbService.getDb1Dual();
    }

    @GetMapping("/db2/getuser")
    public List<USER_MST_PP> selectAction() {
        return dbService.selectUser();
    }

    @DeleteMapping("/db2/delete/{id}")
    public void deleteAction(@PathVariable String user_id) {
        dbService.deleteUSer(user_id);
    }

    @PutMapping("/db2/insert")
    public USER_MST_PP insertAction(@RequestBody USER_MST_PP ump) {
        return dbService.insertUser(ump);
    }

    @PostMapping("/db2/update")
    public USER_MST_PP updateAction(@RequestBody USER_MST_PP ump) {
        return dbService.updateUser(ump);
    }

    @PostMapping("/sendwait")
    public Todo sendwait(@RequestBody Todo todo) throws JMSException {
        return publishCom.sendWait(todo);    }


}
