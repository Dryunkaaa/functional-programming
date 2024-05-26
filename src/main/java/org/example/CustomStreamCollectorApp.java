package org.example;

import org.example.bean.User;
import org.example.utils.Users;

import java.util.Stack;
import java.util.StringJoiner;
import java.util.stream.Collector;

public class CustomStreamCollectorApp {
    public static void main(String[] args) {
        Stack<String> emailsStack = Users.generateUsers(15)
                .stream()
                .map(User::email)
                .collect(toStack());

        String joinedEmails = emailsStack.stream()
                .collect(toJoinString(" | "));

        System.out.println(joinedEmails);
    }

    private static <T> Collector<T, ?, Stack<T>> toStack() {
        return Collector.of(Stack::new, Stack::push, (stack1, stack2) -> {
            stack2.forEach(stack1::push);
            return stack1;
        });
    }

    private static Collector<String, StringJoiner, String> toJoinString(CharSequence delimiter) {
        return Collector.of(
                () -> new StringJoiner(delimiter),
                StringJoiner::add,
                (x, y) -> x.add(y.toString()),
                StringJoiner::toString
        );
    }

}
