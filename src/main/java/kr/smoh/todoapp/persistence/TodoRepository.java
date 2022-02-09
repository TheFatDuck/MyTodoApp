/**
 * @Package: kr.smoh.todoapp.persistence
 * @File: TodoRepository.java
 * @Date: 2022/01/28 10:57 PM
 * @Author: smoh
 * @Desc: A repository for TodoEntity.
 * @History:
**/

package kr.smoh.todoapp.persistence;

import kr.smoh.todoapp.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// Spring use MethodInterceptor AOP interface.
public interface TodoRepository extends JpaRepository<TodoEntity, String /*Type of PK*/> {
    List<TodoEntity> findByUserId(String userId);
}
