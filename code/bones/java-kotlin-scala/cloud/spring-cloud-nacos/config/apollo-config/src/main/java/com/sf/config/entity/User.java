package com.sf.config.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class User {
    private  int id;
    private String name;
    private String password;


}
