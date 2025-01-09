package saucedemo.com.tests;

import saucedemo.com.pages.User;

public class UserTests {
    public static void main(String[] args) {
        // Создаём пользователя
        User user = new User("standard_user", "secret_sauce");

        // Проверяем геттеры
        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());

        // Изменяем данные с помощью сеттеров
        user.setUsername("locked_out_user");
        user.setPassword("new_secret_sauce");

        // Проверяем изменения
        System.out.println("Updated Username: " + user.getUsername());
        System.out.println("Updated Password: " + user.getPassword());
    }
}
