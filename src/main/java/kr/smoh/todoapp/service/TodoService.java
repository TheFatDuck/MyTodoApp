/**
 * @Package: kr.smoh.todoapp.service
 * @File: TodoService.java
 * @Date: 2022/01/28 10:13 PM
 * @Author: smoh
 * @Desc: Main service for todoapp
 * @History:
 *  22.02.09. smoh Added @Slf4j and service implementation.
**/

package kr.smoh.todoapp.service;

import kr.smoh.todoapp.model.TodoEntity;
import kr.smoh.todoapp.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;
    public String testService(){
        // Create TodoEntity
        TodoEntity todoEntity = TodoEntity.builder().title("My First Todo Item").build();
        // Save TodoEntity
        todoRepository.save(todoEntity);
        // Search TodoEntity
        TodoEntity savedEntity = todoRepository.findById(todoEntity.getId()).get();
        return savedEntity.getTitle();
    }

    public List<TodoEntity> create(final TodoEntity entity){
        //01. Validation.
        validate(entity);
        //02. Save.
        todoRepository.save(entity);
        log.info("Entity Id: {} is saved.", entity.getId());
        //03. Return new list.
        return todoRepository.findByUserId(entity.getUserId());
    }

    private void validate(final TodoEntity entity){
        if(entity == null){
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
        }
        if(entity.getUserId() == null){
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }

    public List<TodoEntity> retrieve(final String userId){
        return todoRepository.findByUserId(userId);
    }

    public List<TodoEntity> update(final TodoEntity entity){
        validate(entity);
        final Optional<TodoEntity> original = todoRepository.findById(entity.getId());
        //Lambda expression
        original.ifPresent(todoEntity -> {
            todoEntity.setTitle(entity.getTitle());
            todoEntity.setDone(entity.isDone());
            todoRepository.save(todoEntity);
        });
        return retrieve(entity.getUserId());
    }

    public List<TodoEntity>delete(final TodoEntity entity){
        validate(entity);
        try {
            todoRepository.delete(entity);
        }
        catch(Exception ex){
            log.error("Error occurred during delete entity ", entity.getId(), ex);
            throw new RuntimeException("Error occurred during delete entity " + entity.getId());
        }
        return retrieve(entity.getUserId());
    }
}
