package com.sf.jkt.j.spring.biz.resttemplate.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "person")
public class Person {
 
 
    private String lastName;
    private int age;
    private Boolean boss;
    private Date birth;
 
    private Map<String, Object> maps;
    private List<Object> lists;
    private Dog dog;
    private Integer[] array;
 
 
    public String getLastName() {
        return lastName;
    }
 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 
    public Integer[] getArray() {
        return array;
    }
 
    public void setArray(Integer[] array) {
        this.array = array;
    }
 
    public int getAge() {
        return age;
    }
 
    public void setAge(int age) {
        this.age = age;
    }
 
    public Boolean getBoss() {
        return boss;
    }
 
    public void setBoss(Boolean boss) {
        this.boss = boss;
    }
 
    public Date getBirth() {
        return birth;
    }
 
    public void setBirth(Date birth) {
        this.birth = birth;
    }
 
    public Map<String, Object> getMaps() {
        return maps;
    }
 
    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }
 
    public List<Object> getLists() {
        return lists;
    }
 
    public void setLists(List<Object> lists) {
        this.lists = lists;
    }
 
    public Dog getDog() {
        return dog;
    }
 
    public void setDog(Dog dog) {
        this.dog = dog;
    }
 
    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", birth=" + birth +
                ", maps=" + maps +
                ", lists=" + lists +
                ", dog=" + dog +
                ", array=" + Arrays.toString(array) +
                '}';
    }
}

