package org.example.utils;

import org.example.bean.User;

import java.util.List;
import java.util.stream.IntStream;

public final class Users {

    private Users() {

    }

    public static List<User> generateUsers(int count) {
        return IntStream.range(1, count)
                .mapToObj(Users::randomUser)
                .toList();
    }

    public static User randomUser(int id) {
        return new User(
                id,
                "username (%d)".formatted(id),
                "email (%d)".formatted(id)
        );
    }

}
