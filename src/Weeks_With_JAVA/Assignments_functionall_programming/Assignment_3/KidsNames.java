package Weeks_With_JAVA.Assignments_functionall_programming.Assignment_3;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class KidsNames {

    public static List<String> getKidNames(List<Person> people) {
        return people.stream()
                .filter(person -> person.getAge() < 18)
                .map(Person::getName).collect(toList());
    }

    public static class Person {
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        private final String name;
        private final int age;

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

}