import java.util.*;

// this class perform all methods
class Hexa {

    // map store for hex val to decimal val convert
    private final HashMap<Character, Integer> hexaDecimalToDecimalMap = new HashMap<>();

    Hexa() {
        hexaDecimalToDecimalMap.put('A', 10);
        hexaDecimalToDecimalMap.put('B', 11);
        hexaDecimalToDecimalMap.put('C', 12);
        hexaDecimalToDecimalMap.put('D', 13);
        hexaDecimalToDecimalMap.put('E', 14);
        hexaDecimalToDecimalMap.put('F', 15);
    }

    /**
     * convert decimal to hexadecimal
     * 
     * @param decimal number
     * @return hexa decimal number
     */
    public String decToHex(int dec) {
        if (dec == 0)
            return "0";

        StringBuilder sb = new StringBuilder();
        while (dec > 0) {
            int val = dec % 16;
            if (val > 9)
                sb.append((char) ('A' + (val - 10)));
            else
                sb.append(val);

            dec /= 16;
        }

        String hex = sb.reverse().toString();
        return hex;
    }

    /**
     * convert hexadecimal to decimal
     * 
     * @param hexa decimal number
     * @return decimal number
     */
    public int hexToDec(String hex) {
        int dec = 0;
        int n = hex.length();
        int i = 0;

        while (n - 1 >= 0) {
            char ch = hex.charAt(i);
            if (hexaDecimalToDecimalMap.containsKey(ch))
                dec += hexaDecimalToDecimalMap.get(ch) * Math.pow(16, n - 1);
            else
                dec += (ch - '0') * Math.pow(16, n - 1);
            n--;
            i++;
        }

        return dec;
    }

    /**
     * addtion hexa decimal value
     * 
     * @param the first string is hexa decimal
     * @param the secound string is decimal number
     * @return integer value
     */
    public int hexAddtionMethod(String s1, String s2) {
        int num1 = hexToDec(s1);
        int num2 = hexToDec(s2);
        return num1 + num2;
    }

    /**
     * subscription hexa decimal value
     * 
     * @param the first string is hexa decimal
     * @param the secound string is decimal number
     * @return integer value
     */
    public int hexSubtractionMethod(String s1, String s2) {
        int num1 = hexToDec(s1);
        int num2 = hexToDec(s2);
        if (num1 >= num2)
            return num1 - num2;
        return -1;
    }

    /**
     * multiplition hexa decimal value
     * 
     * @param the first string is hexa decimal
     * @param the secound string is decimal number
     * @return integer value
     */
    public int hexMultiplitionMethod(String s1, String s2) {
        int num1 = hexToDec(s1);
        int num2 = hexToDec(s2);
        return num1 * num2;
    }

    /**
     * division hexa decimal
     * 
     * @param the first string is hexa decimal
     * @param the secound string is decimal number
     * @return integer value
     */
    public int hexDivisionMethod(String s1, String s2) {
        int num1 = hexToDec(s1);
        int num2 = hexToDec(s2);
        if (num1 < num2)
            return 0;
        return num1 / num2;
    }

    /**
     * compair two string if s1 is less and s2 is greater
     * 
     * @param the first string is hexa decimal
     * @param the secound string is decimal number
     * @return boolean value
     */
    boolean isGreater(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        if (n > m) {
            return true;
        } else if (n < m) {
            return false;
        } else {
            for (int i = 0; i < n; i++) {
                char ch1 = s1.charAt(i);
                char ch2 = s2.charAt(i);

                int num1;
                int num2;

                if (!hexaDecimalToDecimalMap.containsKey(ch1) && !hexaDecimalToDecimalMap.containsKey(ch2)) {
                    num1 = ch1 - '0';
                    num2 = ch2 - '0';
                } else {
                    num1 = hexaDecimalToDecimalMap.get(ch1);
                    num2 = hexaDecimalToDecimalMap.get(ch2);
                }

                if (num1 > num2) {
                    return true;
                } else if (num1 < num2) {
                    return false;
                } else {
                    continue;
                }
            }
        }
        return false;
    }

    /**
     * compair two string if s1 is less and s2 is greater
     * 
     * @param the first string is hexa decimal
     * @param the secound string is decimal number
     * @return boolean value
     */
    boolean isLess(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        if (n > m) {
            return false;
        } else if (n < m) {
            return true;
        } else {
            for (int i = 0; i < n; i++) {
                char ch1 = s1.charAt(i);
                char ch2 = s2.charAt(i);

                int num1;
                int num2;

                if (!hexaDecimalToDecimalMap.containsKey(ch1) && !hexaDecimalToDecimalMap.containsKey(ch2)) {
                    num1 = ch1 - '0';
                    num2 = ch2 - '0';
                } else {
                    num1 = hexaDecimalToDecimalMap.get(ch1);
                    num2 = hexaDecimalToDecimalMap.get(ch2);
                }

                if (num1 > num2) {
                    return false;
                } else if (num1 < num2) {
                    return true;
                } else {
                    continue;
                }
            }
        }
        return false;
    }
}

class HexaCal {
    public static void main(String[] args) {
        Hexa hexa = new Hexa();

        String s1 = "A3F";
        String s2 = "A5F";

        int num1 = hexa.hexToDec(s1);
        int num2 = hexa.hexToDec(s2);

        System.out.println("First value in decimal: " + num1);
        System.out.println("Secound value in decimal: " + num2);

        // methods calling
        int addition = hexa.hexAddtionMethod(s1, s2);
        int subtraction = hexa.hexSubtractionMethod(s1, s2);
        int multiplition = hexa.hexMultiplitionMethod(s1, s2);
        int division = hexa.hexDivisionMethod(s1, s2);

        System.out.println(s1 + " and " + s2 + " Hexa Addition : " + hexa.decToHex(addition));
        if (subtraction == -1) {
            System.out.println("first value is greater so subscription is not possible.");
        } else {
            System.out.println(s1 + " and " + s2 + " Hexa subscription : " + hexa.decToHex(subtraction));
            System.out.println(s1 + " and " + s2 + " Hexa Multiplication : " + hexa.decToHex(multiplition));
            System.out.println(s1 + " and " + s2 + " Hexa Division : " + hexa.decToHex(division));
        }

        if (hexa.isGreater(s1, s2)){
            System.out.println("S1 is greater");
        }else if (hexa.isLess(s1, s2)){
            System.out.println("S2 is greater");
        }else{
            System.out.println("s1 and s2 are equal");
        }
    }
}