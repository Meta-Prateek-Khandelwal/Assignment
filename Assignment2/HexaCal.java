package Assignment2;
import java.util.*;

// this class perform all methods
class Hex{

    // store for hex val to decimal val convert
    HashMap<Character, Integer> map = new HashMap<>();
    Hex(){
        map.put('A', 10);
        map.put('B', 11);
        map.put('C', 12);
        map.put('D', 13);
        map.put('E', 14);
        map.put('F', 15);
    }
    
    // convert decimal to hexadecimal
    String decToHex(int dec){
        if(dec == 0) return "0";
        
        StringBuilder sb = new StringBuilder();
        while(dec > 0){
           int val = dec % 16;
           if(val > 9) sb.append((char)('A' + (val-10)));
           else sb.append(val);
           
           dec /= 16;
        }
        
        String hex = sb.reverse().toString();
        return hex;
    }
    
    // 10*16^2
    // convert hexadecimal to decimal
    int hexToDec(String hex){//A3F
        int dec = 0;
        int n = hex.length();
        int i = 0;
        
        while(n-1 >= 0){//'3'-3//67
            char ch = hex.charAt(i);//A-3-F
            if(map.containsKey(ch)) dec += map.get(ch) * Math.pow(16, n-1);//2560+48+15
            else dec += (ch-'0') * Math.pow(16, n-1);//48+2560
            n--;//2-1-0
            i++;//1-2-3
        }

        return dec;
    }
    
    // addtion hex val perform
    int hexAdd(int num1, int num2){
        return num1 + num2;
    }
    
    // subscription hex val perform
    int hexSub(int num1, int num2){
        if(num1 >= num2) return num1 - num2;
        return -1;
    }
    
    // multiplition hex val perform
    int hexMul(int num1, int num2){
        return num1 * num2;
    }
    
    // division hex val perform
    int hexDiv(int num1, int num2){
        if(num1 < num2) return 0;
        return num1/ num2;
    }
    
    // compair for both hex value 
    boolean hexCompair(String s1, String s2, boolean[] isGreater, boolean[] isLess, boolean[] isEqual){
        int n = s1.length();
        int m = s2.length();
        
        if(n > m) {
            isGreater[0] = true;
            return true;
        }
        else if(n < m) {
            isLess[0] = true;
            return true;
        }
        else{
            for(int i = 0; i < n; i++){
                char ch1 = s1.charAt(i);
                char ch2 = s2.charAt(i);
                
                int num1;
                int num2;
                
                if(!map.containsKey(ch1) && !map.containsKey(ch2)){
                    num1 = ch1 - '0';
                    num2 = ch2 - '0';
                }else{
                    num1 = map.get(ch1);
                    num2 = map.get(ch2);
                }
                
                if(num1 > num2) {
                    isGreater[0] = true;
                    return true;
                }
                else if(num1 < num2) {
                    isLess[0] = true;
                    return true;
                }
                else continue;
            }
        }
        
        isEqual[0] = true;
        return true;
    }
}

class HexaCal{
    public static void main(String[] args){
        Hex h = new Hex();
        
        String s1 = "A3F";
        String s2 = "A5F";
        
        boolean isGreater[] = {false};
        boolean isLess[] = {false};
        boolean isEqual[] = {false};
        
        int num1 = h.hexToDec(s1);
        int num2 = h.hexToDec(s2);
        
        System.out.println("First value in decimal: "+num1);
        System.out.println("Secound value in decimal: "+num2);
        
        // methods calling
        int add = h.hexAdd(num1, num2);
        int sub = h.hexSub(num1, num2);
        int mul = h.hexMul(num1, num2);
        int div = h.hexDiv(num1, num2);
        boolean compair = h.hexCompair(s1, s2, isGreater, isLess, isEqual);
        
        // print 
        System.out.println(s1+" and "+ s2 +" Hexa Addition : "+ h.decToHex(add));
        if(sub == -1) System.out.println("first value is greater so subscription is not possible.");
        else System.out.println(s1+" and "+ s2 +" Hexa subscription : "+h.decToHex(sub));
        System.out.println(s1+" and "+ s2 +" Hexa Multiplication : "+h.decToHex(mul));
        System.out.println(s1+" and "+ s2 +" Hexa Division : "+h.decToHex(div));
        
        if(isGreater[0]) System.out.println("S1 is greater");
        if(isLess[0]) System.out.println("S2 is greater");
        if(isEqual[0]) System.out.println("s1 and s2 are equal");
    }
}