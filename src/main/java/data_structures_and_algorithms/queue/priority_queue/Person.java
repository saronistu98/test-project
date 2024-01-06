package data_structures_and_algorithms.queue.priority_queue;

import lombok.ToString;

import javax.annotation.Nonnull;

@ToString
class Person implements Comparable<Person> {

    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(@Nonnull Person p) {
        return Integer.compare(this.age, p.age);
    }
}
