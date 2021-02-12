package com.sf.ddd.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInfoDto {
    public long id;
    public String name;
    public int sex;
    public int age;
}
