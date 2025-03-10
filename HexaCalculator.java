import java.util.*;

class Hex{
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
    // String decToHex(int dec){

    // }
    // convert hexadecimal to decimal
    int hexToDec(String hex){
        int dec = 0;
        int n = hex.length();
        int i = 0;
        
        while(n-1 >= 0){
            char ch = hex.charAt(i);
            if(map.containsKey(ch)) dec += map.get(ch) * Math.pow(16, n-1);
            else dec += (ch-'0') * Math.pow(16, n-1);
            n--;
            i++;
        }

        return dec;
    }
}

class HexaCalculator{
    public static void main(String[] args){
        Hex h = new Hex();
        
        System.out.println(h.hexToDec("A5F"));
    }
}