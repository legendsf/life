package com.sf.jkt.j.jdk8.stream;

import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.Collection;

public class AnnotationTest {
    public static void main(String[] args) {
//        testAnnos2();
    }
    public static void testAnnos2(){
        Hint hint = Person3.class.getAnnotation(Hint.class);
        System.out.println(hint);                   // null

        Hints hints1 = Person3.class.getAnnotation(Hints.class);
        System.out.println(hints1.value().length);  // 2

        Hint[] hints2 = Person3.class.getAnnotationsByType(Hint.class);
        System.out.println(hints2.length);          // 2
    }
    public static void testAnnos(){
        Hint hint=Person1.class.getDeclaredAnnotation(Hint.class);
        Hints hints=Person2.class.getAnnotation(Hints.class);
        Hint[] hints3=Person.class.getAnnotationsByType(Hint.class);
        System.out.println(hint);
        System.out.println(hints.value().length);
        System.out.println(hints3.length);
    }
}

@Hint("hint1")
@Hint("hint2")
class Person3 {}

@Hints({@Hint("a"),@Hint("b")})
class Person1{}

@Hint("a")
@Hint("b")
class Person2{

}


@interface  Hints{
    Hint[] value();
}

@Repeatable(Hints.class)
@interface  Hint{
    String value();
}

class Annotations {
    @Retention( RetentionPolicy.RUNTIME )
    @Target( { ElementType.TYPE_USE, ElementType.TYPE_PARAMETER } )
    public @interface NonEmpty {
    }

    public static class Holder< @NonEmpty T > extends @NonEmpty Object {
        public void method() throws @NonEmpty Exception {
        }
    }

    @SuppressWarnings( "unused" )
    public static void main(String[] args) {
        final Holder< String > holder = new @NonEmpty Holder< String >();
        @NonEmpty Collection< @NonEmpty String > strings = new ArrayList<>();
    }
}
