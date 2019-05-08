package com.fjn.tests.java8.stream;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamIntermediateTest {

    List<Integer> arr = Arrays.asList(new Integer[]{1,2,3,3});

//    @Test
    public void testMap(){
        Stream<Integer> stream = arr.stream();
        ArrayList<Integer> list =stream.map(v->v+1)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        list.forEach(v->System.out.print(v+","));
        System.out.println();
    }

//    @Test
    public void testFlatMap(){
        Stream<Integer> stream = arr.stream();
        List<Integer> list =stream.flatMap((v)->{
            List<Integer> r = new ArrayList<>();
            int start = v<=4 ? v : 5;
            for(int i = start; i <= 4; i++){
                r.add(i);
            }
            return r.stream();
        }).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        list.forEach(v->System.out.print(v+","));
        System.out.println();
    }

//    @Test
    public void testFliter(){
        Stream<Integer> stream = arr.stream();
        List<Integer> list = stream.filter(v->v<3)
                .collect(Collectors.toList());
        list.forEach(v->System.out.print(v+","));
        System.out.println();
    }

//    @Test
    public void testPeek(){
        Stream<Integer> stream = arr.stream();
        stream.peek(System.out::print)
                .collect(Collectors.toList())
                .forEach(System.out::print);
        System.out.println();
    }

//    @Test
    public void testUnordered(){
        Stream<Integer> stream = arr.stream();
        stream.unordered()
                .collect(Collectors.toList())
                .forEach(System.out::print);
        System.out.println();
    }


//    @Test
    public void testSorted(){
        Stream<Integer> stream = arr.stream();
        stream.sorted((x,y)->y.compareTo(x))
                .collect(Collectors.toList())
                .forEach(System.out::print);
        System.out.println();
    }

//    @Test
    public void testLimit(){
        Stream<Integer> stream = arr.stream();
        stream.limit(2)
                .collect(Collectors.toList())
                .forEach(System.out::print);
        System.out.println();
    }

//    @Test
    public void testDistinct(){
        Stream<Integer> stream = arr.stream();
        stream.distinct()
                .collect(Collectors.toList())
                .forEach(System.out::print);
        System.out.println();
    }

//    @Test
    public void testSkip(){
        Stream<Integer> stream = arr.stream();
        stream.skip(1)
                .collect(Collectors.toList())
                .forEach(System.out::print);
        System.out.println();
    }
}
