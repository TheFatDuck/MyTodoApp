/**
 * @Package: kr.smoh.todoapp
 * @File: TodoEntity.java
 * @Date: 2022/01/27 11:09 PM
 * @Author: smoh
 * @Desc: Model 과 Entity 를 한 클래스 내부에 구현.
 * @History:
 *      2022/01/28 smoh Add @Entity annotation.
**/

package kr.smoh.todoapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="todo")
public class TodoEntity {
    @Id //PrimaryKey
    @GeneratedValue(generator = "system-uuid") //Generate id automatically using system-uuid generator.
    @GenericGenerator(name = "system-uuid", strategy = "uuid") //Make own custom generator. Create GenericGenerator named "system-uuid" using "uuid" as strategy.
    private String id;
    private String userId;
    private String title;
    private boolean done;
}
