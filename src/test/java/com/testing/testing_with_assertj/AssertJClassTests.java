package com.testing.testing_with_assertj;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class AssertJClassTests {

    private List<Person> persons = List.of(
            new Person("Alice", 25),
            new Person("Bob", 30),
            new Person("Charlie", 28),
            new Person("David", 35),
            new Person("Eve", 27),
            new Person("Frank", 32),
            new Person("Grace", 29),
            new Person("Henry", 31),
            new Person("Ivy", 26),
            new Person("Jack", 33)
    );

    //Extracting AssertJ
    

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    class Person {
        private String name;
        private int age;
    }
}
