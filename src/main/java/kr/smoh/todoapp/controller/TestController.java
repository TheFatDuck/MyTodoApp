/**
 * @Package: kr.smoh.todoapp.controllers
 * @File: TestController.java
 * @Date: 2022/01/28 12:06 AM
 * @Author: smoh
 * @Desc: Test controller for REST api testing.
 * @History:
**/
package kr.smoh.todoapp.controller;

import kr.smoh.todoapp.dto.ResponseDTO;
import kr.smoh.todoapp.dto.TestRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {
    @GetMapping
    //../test
    public String testController(){
        return "Hello world!";
    }
    //../test/testGetMapping
    @GetMapping("/testGetMapping")
    public String testControllerWithPath(){
        return "Goodbye world!";
    }
    //../test/123
    @GetMapping("/{id}")
    public String testControllerWithPathVariables(@PathVariable(required = false) int id){
        return "Hello user " + id + " !";
    }
    //..//test/testRequestParam?id=123
    @GetMapping("/testRequestParam")
    public String testControllerWithRequestParam(@RequestParam(required = false) int id){
        return "Goodbye user " + id + " !";
    }
    //../test/testRequestBody
    //With JSON data: { "id": 123, "message": "Hello?" }
    @GetMapping("/testRequestBody")
    public String testControllerWithRequestBody(@RequestBody TestRequestDTO testRequestDTO){
        return "Hello user " + testRequestDTO.getId() + " ! You've got message: " + testRequestDTO.getMessage();
    }
    //../test/testResponseBody
    @GetMapping("/testResponseBody")
    public ResponseDTO<String> testControllerWithResponseDTO(){
        List<String> list = new ArrayList<>();
        list.add("Hello world! I'm ResponseDTO.");
        return ResponseDTO.<String>builder().data(list).build();
    }
    //../test/testResponseEntity
    @GetMapping("/testResponseEntity")
    public ResponseEntity<?> testControllerWithResponseEntity() {
        List<String> list = new ArrayList<>();
        list.add("Hello world! I'm ResponseEntity. And you get 400!");
        ResponseDTO<String> res = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.badRequest().body(res); //Set HTTP status to 400.
    }
}
