package com.sf.jkt.j.spring.biz.resttemplate.graphql;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Book {
    private Integer id;
    private String name;
    private Author author;
    private String publisher;
}