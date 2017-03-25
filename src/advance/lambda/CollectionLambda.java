package advance.lambda;
/*
 * Created by david on 2017/3/25.
 * Copyright ISOTOPE Studio
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionLambda {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Mars", "Leon", "Frank", "Simon", "Coco", "Angelina", "Doris");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
        Collections.sort(names, (a, b) -> a.compareTo(b));
        Collections.sort(names, String::compareTo);
        names.sort(String::compareTo);

        Consumer<Object> print = System.out::println;
        print.accept(names);


        Student s1 = new Student("Mars", 631);
        Student s2 = new Student("Leon", 512);
        Student s3 = new Student("Frank", 613);
        List<Student> students = Arrays.asList(s1, s2, s3);
        students.sort((a, b) -> a.getId() - b.getId());
        students.sort(Comparator.comparingInt(Student::getId));
        print.accept(students);

        int average = students.stream()
                .collect(Collectors.summingInt(Student::getId)) / students.size();
        int a2 = students.stream().mapToInt(Student::getId).sum() / students.size();
        print.accept(average);
        students.stream().mapToInt(Student::getId).average().ifPresent(System.out::println);

        List<String> list = students.stream().map(Student::getName).collect(Collectors.toList());
        print.accept(list);

        Stream.of("red", "green", "blue")
                .sorted()
                .findFirst()
                .ifPresent(System.out::println);

        print.accept(Stream.of("Mars", "March", "Leon")
                .filter(s -> s.startsWith("M"))
                .collect(Collectors.joining(", ")));

        print.accept(Stream.of("Mars","List")
                .map(String::toUpperCase)
                .collect(Collectors.toList()));
    }

    private static class Student {
        private String name;
        private int id;

        Student(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return "Student: " + name + ", " + id;
        }
    }

}
