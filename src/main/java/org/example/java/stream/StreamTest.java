package org.example.java.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    // 流、中间操作、终端操作
    // 懒惰请求、流只能使用一次
    public static void main(String[] args) {
        // 创建 Stream
        createStream();

        System.out.println("=================================");

        // 中间操作
        intermediateOperations();

        System.out.println("=================================");

        // 终端操作
        terminalOperations();

        System.out.println("================================");

        // 并行流
        parallelStream();

        System.out.println("================================");

        // Collectors 工具类
        collectors();
    }

    private static void createStream() {
        // Stream.of()
        Stream<String> stream1 = Stream.of("A", "B", "C");
        stream1.forEach(System.out::println);

        // Collection.stream()
        List<String> list = Arrays.asList("Apple", "Banana", "Orange");
        Stream<String> stream2 = list.stream();
        stream2.forEach(System.out::println);

        // Arrays.stream()
        int[] numbers = {1, 2, 3, 4, 5};
        Arrays.stream(numbers).forEach(System.out::println);

        // Stream.generate()
        Stream.generate(Math::random).limit(5).forEach(System.out::println);

        // Stream.iterate()
        Stream.iterate(1, n -> n + 2).limit(5).forEach(System.out::println);
    }

    private static void intermediateOperations() {
        // filter()
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.stream().filter(n -> n % 2 == 0).forEach(System.out::println);

        // map()
        List<String> words = Arrays.asList("apple", "banana", "orange");
        words.stream().map(String::toUpperCase).forEach(System.out::println);

        // flatMap()
        List<List<String>> nestedList = Arrays.asList(
                Arrays.asList("A", "B"),
                Arrays.asList("C", "D"));
        nestedList.stream().flatMap(List::stream).forEach(System.out::println);

        // sorted()
        List<Integer> numbers2 = Arrays.asList(3, 1, 4, 2);
        numbers2.stream().sorted().forEach(System.out::println);

        // distinct()
        List<Integer> numbers3 = Arrays.asList(1, 2, 2, 3, 3);
        numbers3.stream().distinct().forEach(System.out::println);

        // limit()
        List<Integer> numbers4 = Arrays.asList(1, 2, 3, 4, 5);
        numbers4.stream().limit(3).forEach(System.out::println);

        // skip()
        List<Integer> numbers5 = Arrays.asList(1, 2, 3, 4, 5);
        numbers5.stream().skip(2).forEach(System.out::println);

        // peek()
        List<Integer> numbers6 = Arrays.asList(1, 2, 3, 4, 5);
        numbers6.stream().peek(n -> System.out.println("Processing: " + n))
                .forEach(System.out::println);
    }

    private static void terminalOperations() {
        // forEach()
        List<String> words = Arrays.asList("apple", "banana", "orange");
        words.stream().map(String::toUpperCase).forEach(System.out::println);

        // collect()
        List<String> words2 = Arrays.asList("apple", "banana", "orange");
        List<String> result = words2.stream().filter(s -> s.startsWith("a"))
                .collect(Collectors.toList());
        System.out.println(result);

        // reduce()
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        int sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum);

        // count()
        List<Integer> numbers2 = Arrays.asList(1, 2, 3);
        long count = numbers2.stream().filter(n -> n % 2 == 0).count();
        System.out.println(count);

        // min() / max()
        List<Integer> numbers3 = Arrays.asList(1, 2, 3, 4, 5);
        numbers3.stream().min(Integer::compare).ifPresent(System.out::println);
        numbers3.stream().max(Integer::compare).ifPresent(System.out::println);

        // findFirst() / findAny()
        List<Integer> numbers4 = Arrays.asList(1, 2, 3, 4, 5);
        numbers4.stream().findAny().ifPresent(System.out::println);

        // anyMatch() / allMatch() / noneMatch()
        List<Integer> numbers5 = Arrays.asList(1, 2, 3, 4, 5);
        boolean anyEven = numbers5.stream().anyMatch(n -> n % 2 == 0);
        boolean allEven = numbers5.stream().allMatch(n -> n % 2 == 0);
        boolean noneEven = numbers5.stream().noneMatch(n -> n % 2 == 0);
        System.out.println(anyEven + ", " + allEven + ", " + noneEven);

        // toArray()
        List<String> words3 = Arrays.asList("A", "B", "C");
        String[] array = words3.stream().filter(s -> !s.equals("B")).toArray(String[]::new);
        System.out.println(Arrays.toString(array));
    }

    private static void parallelStream() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.parallelStream().forEach(n -> System.out.println(n + " processed by " + Thread.currentThread().getName()));
    }

    private static void collectors() {
        // 转换为集合
        List<String> words = Arrays.asList("apple", "banana", "orange");
        List<String> list = words.stream().toList();
        Set<String> set = new HashSet<>(words);
        System.out.println(list + ", " + set);

        // 分组
        Map<Character, List<String>> grouped = words.stream().collect(Collectors.groupingBy(s -> s.charAt(0)));
        System.out.println(grouped);

        // 统计
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        IntSummaryStatistics stats = numbers.stream().collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println(stats);
    }

}
