/**
 * @Package: kr.smoh.todoapp
 * @File: TestRequestDTO.java
 * @Date: 2022/01/28 12:26 AM
 * @Author: smoh
 * @Desc: Test DTO for @RequestBody
 * @History:
**/

package kr.smoh.todoapp.dto;

import lombok.Data;

@Data
public class TestRequestDTO {
    private int id;
    private String message;
}
