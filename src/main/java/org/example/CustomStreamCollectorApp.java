package org.example;

import org.example.bean.User;
import org.example.utils.Users;

import java.util.Stack;
import java.util.stream.Collector;

public class CustomStreamCollectorApp {
    public static void main(String[] args) {
        Stack<String> emailsStack = Users.generateUsers(10)
                .stream()
                .map(User::email)
                .collect(toStack());

        while (!emailsStack.empty()) {
            System.out.println(emailsStack.pop());
        }
    }

    private static <T> Collector<T, ?, Stack<T>> toStack() {
        return Collector.of(Stack::new, Stack::push, (stack1, stack2) -> {
            stack2.forEach(stack1::push);
            return stack1;
        });
    }

}
