package com.fjn.tests.java8.function;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class FunctionTest {

    private static class Person {
        String id;
        String name;
        int age;
        Gender gender;

        Person(String id, String name, int age, Gender gender){
            this.id = id;
            this.name = name;
            this.age = age;
            this.gender =gender;
        }

        @Override
        public String toString() {
            return "id: "+id+"\tname: "+name + "\tage: "+age+"\tgender: "+gender;
        }
    }

    enum Gender{
        man,woman
    }

    static Collection<Person> persons = new ArrayList<>();
    static{
        for (int i = 0; i < 100; i++) {
            persons.add(new Person("id_"+i, "name_"+i, i, i%3==0 ? Gender.woman : Gender.man));
        }
    }

    @Test
    public void test0() {
        Collection<Person> selection = doSelection(persons, (person ->person.age>84 && person.gender==Gender.man));
        Consumer<Person> printer =System.out::println;
        selection.forEach(printer);
    }

    @Test
    public void test1() {
        Collection<String> projection = doProjection(persons,
                (Person person) -> {return person.age>84 && person.gender==Gender.man;},
                "name",
                (Person person, String filedName)->{return "name".equals(filedName) ? person.name : "";},
                null
                );
        Consumer<String> printer = System.out::println;
        projection.forEach(printer);
    }

    public void test2(){
        Predicate notNullPredicate = (c) -> c != null;
        System.out.println(notNullPredicate.test(null));
    }

    public static <Row> Collection<Row> doSelection(Collection<Row> table, Predicate<Row> rowWhere) {
        Predicate isNull = (c) -> c == null;
        if(isNull.test(table)){
            return Collections.EMPTY_LIST;
        }

        Collection<Row> result = new ArrayList<>();
        table.forEach(row -> {
            boolean rowAvailable = row !=null && (rowWhere !=null ? rowWhere.test(row) : true);
            if (rowAvailable) {
                result.add(row);
            }
        });
        return result;
    }

    public static <Row, Column> Collection<Column> doProjection(Collection<Row> table, Predicate<Row> rowWhere, String columnName, BiFunction<Row, String, Column> columnGetter, Predicate<Column> columnPredicate) {
        Predicate isNull = (c) -> c == null;
        if(isNull.test(table)){
            return Collections.EMPTY_LIST;
        }

        Collection<Column> result = new ArrayList<>();
        table.forEach(row -> {
            boolean rowAvailable = row !=null && (rowWhere !=null ? rowWhere.test(row) : true);
            if (rowAvailable) {
                Column column = columnGetter.apply(row, columnName);
                boolean columnAvailable =column!=null && (columnPredicate!=null?columnPredicate.test(column) : true);
                if(columnAvailable) {
                    result.add(column);
                }
            }
        });
        return result;
    }
}
