package org.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("Calculator.txt");

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите пример формата (Число пробел операция пробел число)");
        String num1 = sc.next();
        String oper = sc.next();
        String num2 = sc.next();
        String str = "";
        try {
            double numb1 = Double.parseDouble(num1);
            double numb2 = Double.parseDouble(num2);
            double res = 0;
            switch (oper) {
                case "*" -> res = numb1 * numb2;
                case "+" -> res = numb1 + numb2;
                case "-" -> res = numb1 - numb2;
                case "/" -> res = numb1 / numb2;
                default -> throw new Exception("Operation Error!");
            }
            str = Double.toString(res);
        }catch (NumberFormatException e){
            str = "Error! Not number";
        }catch (Exception e ){
            str = e.getMessage();
        }
        System.out.println(str.equals("Infinity") ? "Error! Division by zero" : str);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(str.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}