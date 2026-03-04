package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("Input.txt");

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String filenumber = "";
        String num1 = "", oper = "", num2 = "";
        StringBuilder stringBuffer = new StringBuilder();
        int k = 0;
        try (FileInputStream fis = new FileInputStream(file)){
            int a = fis.read();
            while (a != -1){
                if (a == ' '){
                    if(k == 0) {
                        num1 = stringBuffer.toString();
                        stringBuffer.setLength(0);
                        k++;
                    }else if(k == 1){
                        oper = stringBuffer.toString();
                        stringBuffer.setLength(0);
                        k++;
                    }
                }else{
                    stringBuffer.append((char) a);
                }
//                else {
//                    stringBuffer.append((char) a);
//                    num2 = stringBuffer.toString();
//                    stringBuffer.setLength(0);
//                    k++;
//                }
                a = fis.read();
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            num2 = stringBuffer.toString();
            stringBuffer.setLength(0);
        }
        System.out.println(filenumber);
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Введите пример формата (Число пробел операция пробел число)");
//        num1 = sc.next();
//        oper = sc.next();
//        num2 = sc.next();
        String str = "";
        double numb1 = 0;
        double numb2 = 0;
        try {
            numb1 = Double.parseDouble(num1);
            numb2 = Double.parseDouble(num2);
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
        //System.out.println(str.equals("Infinity") ? "Error! Division by zero" : str);
        str = str.equals("Infinity") ? "Error! Division by zero" : str;
        String answer = "";
        if(Character.isDigit(str.charAt(0))) {
             answer = numb1 + " " + oper + " " + numb2 + " = " + str;
        }else {
            answer = str;
        }
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(answer.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}