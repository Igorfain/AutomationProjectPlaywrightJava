package saucedemo.com.pages;

public class User {
    private String username;
    private String password;

    // Конструктор
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Геттеры
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Сеттеры
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
