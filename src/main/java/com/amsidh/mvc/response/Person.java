package com.amsidh.mvc.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Person {
    private Integer id;
    private String name;
}