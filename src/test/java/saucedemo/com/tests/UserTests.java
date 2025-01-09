package saucedemo.com.tests;

import saucedemo.com.pages.User;

public class UserTests {
    public static void main(String[] args) {
        // Crating user object with username and password
        User user = new User("standard_user", "secret_sauce");

        // Verifying the getters
        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());

        //Changing the user object values
        user.setUsername("locked_out_user");
        user.setPassword("new_secret_sauce");

        // Verifying the updated values using getters
        System.out.println("Updated Username: " + user.getUsername());
        System.out.println("Updated Password: " + user.getPassword());
    }
}
