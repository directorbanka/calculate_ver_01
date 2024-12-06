import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        try {
            System.out.println(calculate(input));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String calculate(String input) throws Exception {
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            throw new Exception("throws Exception");
        }

        String num1 = parts[0];
        String operator = parts[1];
        String num2 = parts[2];

        boolean isRoman = isRoman(num1) && isRoman(num2);
        boolean isArabic = isArabic(num1) && isArabic(num2);

        if (!isRoman && !isArabic) {
            throw new Exception("throws Exception");
        }

        int a = isRoman ? romanToInt(num1) : Integer.parseInt(num1);
        int b = isRoman ? romanToInt(num2) : Integer.parseInt(num2);

        if (a > 10 || b < 1 || b > 10) {
            throw new Exception("throws Exception");
        }

        int result = switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;

            default -> throw new Exception("throws Exception");
        };

        if (isRoman) {
            if (result < 1) {
                throw new Exception("throws Exception");
            }
            return intToRoman(result);
        }

        return String.valueOf(result);
    }

    public static boolean isRoman(String value) {
        return value.matches("I|II|III|IV|V|VI|VII|VIII|IX|X");
    }

    public static boolean isArabic(String value) {
        return value.matches("[0-9]|10");
    }

    public static int romanToInt(String roman) {
        return switch (roman) {
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

    public static String intToRoman(int num) {
        String[] romanNumerals = {
                "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
        };
        int[] values = {100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                result.append(romanNumerals[i]);
                num -= values[i];
            }
        }
        return result.toString();
    }
}