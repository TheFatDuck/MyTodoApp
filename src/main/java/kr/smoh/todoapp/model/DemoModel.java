/**
 * @Package: kr.smoh.todoapp
 * @File: DemoModel.java
 * @Date: 2022/01/27 8:12 PM
 * @Author: smoh
 * @Desc: Lombok testing.
 * @History:
**/

package kr.smoh.todoapp.model;

import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class DemoModel {
    @NonNull
    private String id;
}
