package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamApiApp {
    public static void main(String[] args) {
        Optional<String> emailWithMaxLength = randomUsers(15)
                .stream()
                .filter(u -> u.id > 3)
                .filter(u -> u.id > 4)
                .map(u -> u.email)
                .max(Comparator.comparing(String::length));

        emailWithMaxLength.ifPresent(System.out::println);
    }

    private static List<User> randomUsers(int count) {
        return IntStream.range(1, count)
                .mapToObj(StreamApiApp::randomUser)
                .collect(Collectors.toList());
    }

    private static User randomUser(int id) {
        return new User(
                id,
                "username (%d)".formatted(id),
                "email (%d)".formatted(id)
        );
    }

    record User(long id, String username, String email) {
    }
}
