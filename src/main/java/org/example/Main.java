package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        File file = new File("Input.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //String num1 = "", oper = "", num2 = "";
        ArrayList<String> num1 = new ArrayList<>();
        ArrayList<String> oper = new ArrayList<>();
        ArrayList<String> num2 = new ArrayList<>();
        StringBuilder stringBuffer = new StringBuilder();
        int a = 0;
        String answer = "";
        StringBuilder result = new StringBuilder();
        int k = 0;
        try (FileInputStream fis = new FileInputStream(file)) {
            a = fis.read();
            while (a != -1) {
                if (a == ' ') {
                    if (k == 0) {
                        num1.add(stringBuffer.toString());
                        stringBuffer.setLength(0);
                        k++;
                    } else if (k == 1) {
                        oper.add(stringBuffer.toString());
                        stringBuffer.setLength(0);
                        k++;
                    }
                } else if (a == '\n') {
                    num2.add(stringBuffer.toString());
                    stringBuffer.setLength(0);
                    k = 0;
                } else {
                    stringBuffer.append((char) a);
                }
                a = fis.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (!stringBuffer.toString().equals("")) {
                num2.add(stringBuffer.toString());
            }
            stringBuffer.setLength(0);
        }
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Введите пример формата (Число пробел операция пробел число)");
//        num1 = sc.next();
//        oper = sc.next();
//        num2 = sc.next();
        String str = "";
        double numb1 = 0;
        double numb2 = 0;
        boolean flag = true;
        for (int i = 0; i < num2.size(); i++) {
            try {
                numb1 = Double.parseDouble(num1.get(i));
                numb2 = Double.parseDouble(num2.get(i));
                double res = 0;
                switch (oper.get(i)) {
                    case "*" -> res = numb1 * numb2;
                    case "+" -> res = numb1 + numb2;
                    case "-" -> res = numb1 - numb2;
                    case "/" -> res = numb1 / numb2;
                    default -> throw new Exception("Operation Error!");
                }
                str = Double.toString(res);
            } catch (NumberFormatException e) {
                str = "Error! Not number";
            } catch (Exception e) {
                str = e.getMessage();
                flag = false;
            }
            //System.out.println(str.equals("Infinity") ? "Error! Division by zero" : str);
            str = str.equals("Infinity") ? "Error! Division by zero" : str;
            if (flag && oper.size() != 0) {
                answer = numb1 + " " + oper.get(i) + " " + numb2 + " = " + str;
            }else {
                answer = str;
            }

            result.append(answer + '\n');
        }
        answer = result.toString();

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(answer.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}