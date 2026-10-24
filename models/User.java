package com.telusko.RedisDemoProject.models;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable  //bcz user class needs to convert into bytes and vice versa
{
    private String userId;

    private String name;

    private String phone;

    private String email;

}
