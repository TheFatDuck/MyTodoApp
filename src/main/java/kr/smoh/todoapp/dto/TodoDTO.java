/**
 * @Package: kr.smoh.todoapp
 * @File: TodoDTO.java
 * @Date: 2022/01/27 11:38 PM
 * @Author: smoh
 * @Desc: Request DTO
 * @History:
 *   22.02.09 smoh Added methods.
**/

package kr.smoh.todoapp.dto;

import kr.smoh.todoapp.model.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO {
    private String id;
    private String title;
    private boolean done;

    public TodoDTO(final TodoEntity entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.done = entity.isDone();
    }

    public static TodoEntity toEntity(final TodoDTO dto){
        return TodoEntity.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .done(dto.isDone())
                .build();
    }
}
