package com.company;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    static void parseNumbers(String s1, String s2) throws Exception {
        if (s1.length() < 1 || s2.length() < 1)
            throw new Exception("Number(s) is(are) missing");
    }

    static void parseOperation(String str) throws Exception {
        if (str.length() != 1)
            throw new Exception("Wrong operation");

        char c = str.charAt(0);
        if (c != '+' && c != '-' && c != '*' && c != '/')
            throw new Exception("Wrong operation");
    }

    static boolean containsAllRomChars(String str){
        return switch (str){
            case "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X" -> true;
            default -> false;
        };
    }

    static int romanCharToInt(char c) {
        return switch (c) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            default -> 0;
        };
    }

    static String intNumToRom(int n) {
        return switch (n) {
            case 1 -> "I";
            case 2 -> "II";
            case 3 -> "III";
            case 4 -> "IV";
            case 5 -> "V";
            case 6 -> "VI";
            case 7 -> "VII";
            case 8 -> "VIII";
            case 9 -> "IX";
            case 10 -> "X";
            default -> "";
        };
    }

    static int romanToInt(String s) {
        return switch (s){
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> 0;
        };
    }

    static String intToRoman(int num) throws Exception {
        String res = "";
        if (num <= 0)
            throw new Exception("Negative Roman");

        if (num == 100)
            return "С";
        if (num <= 10)
            return intNumToRom(num);
        if (num > 90 && num < 100)
            return "X" + intNumToRom(num % 10);
        if (num == 90)
            return "XC";
        if (num > 50 && num < 90) {
            res += "L";
            num -= 50;
            while (num - 10 > 0) {
                res += "X";
                num-= 10;
            }
            res += intNumToRom(num);
            return res;
        }
        if (num == 50)
            return "L";
        if (num >= 40 && num < 50){
            res += "XL" + intNumToRom(num % 10);
            return res;
        }
        if (num < 40){
            while (num - 10 > 0){
                res += "X";
                num -=10;
            }
        }
        res += intNumToRom(num % 10);
        return res;
    }

    static String calcFinalDigit(int num1, String operation, int num2){
        switch (operation) {
            case "+" -> {
                return  "" + (num1 + num2);
            }
            case "-" -> {
                return "" + (num1 - num2);
            }
            case "*" -> {
                return "" + (num1 * num2);
            }
            case "/" -> {
                if (num2 != 0)
                    return "" + (num1 / num2);
                else if (num1 == 0)
                    return "WTF"; // DIV ON 0 RIGHT HERE!!!!!!!!!!!!!!!!!!!!!
                else
                    return "INF";
            }
            default -> {
                return null;
            }
        }
    }

    static boolean isRoman(char c){
        return switch (c){
            case 'I', 'V', 'X' -> true; //, 'L', 'C', 'D', 'M'
            default -> false;
        };
    }

    public static String calc(String input) throws Exception {
        String[] str = input.split(" ");
        String res;

        if (str.length != 3)
            throw new Exception("Not a valid operation");
        parseOperation(str[1]);
        parseNumbers(str[0], str[2]);
        if (
                ((str[0].charAt(0) >= '0' && str[0].charAt(0) <= '9' && str[0].length() == 1) || str[0].equals("10"))
                &&
                        ((str[2].charAt(0) >= '0' && str[2].charAt(0) <= '9' && str[2].length() == 1) || str[2].equals("10"))
        ) {
            res = calcFinalDigit(Integer.parseInt(str[0]), str[1], Integer.parseInt(str[2]));
        } else if (containsAllRomChars(str[0]) && containsAllRomChars(str[2])) {
            res = calcFinalDigit(romanToInt(str[0]), str[1], romanToInt(str[2]));
            res = intToRoman(Integer.parseInt(res));
        } else {
            throw new Exception("Wrong numbers");
        }
        return res;
    }

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            while (true)
            {
                String str = in.nextLine();
                if (Objects.equals(str, "exit"))
                    System.exit(0);
                System.out.println(calc(str));
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
