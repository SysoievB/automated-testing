package com.testing.testing_with_assertj;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

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

    @Test
    void extract_and_compare_just_persons_fields() {
        assertThat(persons)
                .extracting("name")
                .contains("Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Henry", "Ivy", "Jack");

        assertThat(persons)
                .extracting(Person::name)
                .contains("Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Henry", "Ivy", "Jack")
                .doesNotContain("Valera");

        assertThat(persons)
                .extracting(Person::name, Person::age)
                .contains(tuple("Alice", 25))
                .doesNotContain(tuple("Valera", 70));
    }

    @Test
    void flat_extracting_and_compare_just_persons_fields() {
        assertThat(persons)
                .flatExtracting(Person::name)
                .doesNotContain("Valera")
                .contains("Alice");
    }

    @Test
    void satisfies_persons() {
        assertThat(persons)
                .satisfies(v -> {
                    assertThat(v).isNotNull();
                    assertThat(v).isNotEmpty();
                    assertThat(v).hasSize(10);
                    assertThat(v).isInstanceOf(List.class);
                    assertThat(v).hasOnlyElementsOfType(Person.class);
                });
    }

    @Test
    void exception_check() {
        NullPointerException cause = new NullPointerException("boom");
        Throwable throwable = new Throwable(cause);

        assertThat(throwable)
                .hasCause(cause)
                .hasCauseInstanceOf(NullPointerException.class)
                .hasCauseInstanceOf(RuntimeException.class)
                .hasCauseExactlyInstanceOf(NullPointerException.class);
    }

    @Test
    void recursive_comparison() {
        Person valera = new Person("Valera", 30);
        Person ivan = new Person("Ivan", 30);

        assertThat(valera).usingRecursiveComparison()
                .ignoringFields("name")
                .isEqualTo(ivan);
    }

    @Test
    void filter_on() {
        assertThat(persons)
                .filteredOn(v -> v.age() > 30)
                .extracting(Person::age)
                .containsExactlyInAnyOrder(31, 35, 32, 33);
    }

    @Test
    void returns() {
        assertThat(persons)
                .first()
                .returns("Alice",Person::name)
                .returns(25,Person::age);
    }

    record Person(String name, int age) {
    }
}
