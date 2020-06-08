package com.sf.jkt.j.spring.biz.resttemplate.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/***
 * https://www.jianshu.com/p/d6aeeddccaf3
 *
 * API:  http://localhost:8081/graphiql?query=%7B%0A%20%20findBooks%7B%0A%20%20%20%20id%2C%0A%20%20%20%20name%0A%20%20%7D%0A%7D
 */
@Component
public class BookQueryResolver implements GraphQLQueryResolver {
    public List<Book> findBooks() {
        Author author = Author.builder()
                .id(1)
                .name("Bill Venners")
                .age(40)
                .build();
        Book b = Book.builder()
                .id(1)
                .name("scala编程第三版")
                .author(author)
                .publisher("电子工业出版社")
                .build();
        List<Book> bookList = new ArrayList<Book>();
        bookList.add(b);
        return bookList;
    }
}