/**
 * @Package: kr.smoh.todoapp.controller
 * @File: TodoController.java
 * @Date: 2022/01/28 9:54 PM
 * @Author: smoh
 * @Desc: Main controller for todoapp
 * @History:
 *   22.02.09. smoh Added createTodo method.
**/

package kr.smoh.todoapp.controller;

import kr.smoh.todoapp.dto.ResponseDTO;
import kr.smoh.todoapp.dto.TodoDTO;
import kr.smoh.todoapp.model.TodoEntity;
import kr.smoh.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
public class TodoController {

    @Autowired
    private TodoService service;

    @GetMapping("/ping")
    public ResponseEntity<?> ping(){
        List<String> list = new ArrayList<>();
        list.add("pong");
        ResponseDTO<String> res = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/test")
    public ResponseEntity<?> testTodo(){
        String str = service.testService();
        List<String> list = new ArrayList<>();
        list.add(str);
        ResponseDTO<String> res = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.ok().body(res);
    }

    @PostMapping
    /**
     * curl --location --request POST 'localhost:8080/todo' \
     * --header 'Content-Type: application/json' \
     * --data-raw '{"title": "A new post."}'
     */
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto){
        try{
            String tempUserId = "temp-user-id";
            //01. Convert to TodoEntity.
            TodoEntity entity = TodoDTO.toEntity(dto);
            //02. Initialize id as null.
            entity.setId(null);
            //03. Set temp user id.
            //TODO: Add auth.
            entity.setUserId(tempUserId);
            //04. Create todoEntity using service.
            List<TodoEntity> entities = service.create(entity);
            //05. Convert returned entities to List<TodoDTO> using java stream.
            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
            //06. Initialize ResponseDTO using dtos.
            ResponseDTO<TodoDTO> res = ResponseDTO.<TodoDTO>builder().data(dtos).build();
            //07. Return res.
            return ResponseEntity.ok().body(res);
        }
        catch(Exception ex){
            String err = ex.getMessage();
            ResponseDTO<TodoDTO> res = ResponseDTO.<TodoDTO>builder().error(err).build();
            return ResponseEntity.badRequest().body(res);
        }
    }

    @GetMapping
    /**
     * curl --location --request GET 'localhost:8080/todo'
     */
    public ResponseEntity<?> retrieveTodoList(){
        String tempUserId = "temp-user-id";
        List<TodoEntity> entries = service.retrieve(tempUserId);
        List<TodoDTO> dtos = entries.stream().map(TodoDTO::new).collect(Collectors.toList());
        ResponseDTO<TodoDTO> res = ResponseDTO.<TodoDTO>builder().data(dtos).build();
        return ResponseEntity.ok().body(res);
    }
}
