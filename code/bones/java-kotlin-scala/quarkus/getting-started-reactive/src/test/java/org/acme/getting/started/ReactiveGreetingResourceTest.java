package org.acme.getting.started;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ReactiveGreetingResourceTest {

    public void testHelloEndpoint() {
        given()
          .when().get("/hello/index”")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy Reactive"));
    }

}