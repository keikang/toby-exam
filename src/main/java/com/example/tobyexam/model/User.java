package com.example.tobyexam.model;

import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    String id;

    String name;

    String password;


}
