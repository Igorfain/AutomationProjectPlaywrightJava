package saucedemo.com.tests;

public class Calculator {
    public void add(int a, int b) {
        System.out.println("Сумма: " + (a + b));
        this.printEnd(); // Вызов метода текущего объекта
    }

    public void printEnd() {
        System.out.println("Операция завершена.");
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.add(3, 5);
    }
}
