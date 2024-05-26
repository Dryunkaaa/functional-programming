package org.example;

import org.example.bean.User;
import org.example.utils.Users;

import java.util.Comparator;
import java.util.Optional;

public class StreamApiApp {
    public static void main(String[] args) {
        Optional<String> emailWithMaxLength = Users.generateUsers(15)
                .stream()
                .filter(u -> u.id() > 3)
                .filter(u -> u.id() > 4)
                .map(User::email)
                .max(Comparator.comparing(String::length));

        emailWithMaxLength.ifPresent(System.out::println);
    }

}
