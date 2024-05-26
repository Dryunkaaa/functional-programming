package org.example;

public class OwnFunctionalInterfaceApp {
    public static void main(String[] args) {
        MyFunction<String, Integer> parseIntFunction = Integer::parseInt;
        MyFunction<String, Integer> squareStringInt = parseIntFunction.andThen(x -> x * x);

        System.out.println(squareStringInt.apply("4")); // should print 16
    }

    @FunctionalInterface
    interface MyFunction<T, R> {
        R apply(T element);

        default <U> MyFunction<T, U> andThen(MyFunction<R, U> afterFunction) {
            return x -> afterFunction.apply(apply(x));
        }
    }
}