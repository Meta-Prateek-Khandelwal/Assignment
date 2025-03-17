package Assignment3;
import java.util.*;

class Operations{
    // create hashMap for the convert lower to upper and vice-versa
    HashMap<Character, Character> lower = new HashMap<>();
    HashMap<Character, Character> upper = new HashMap<>();

    Operations(){
        for(int i = 0; i < 26; i++){
            lower.put((char)(i + 'a'), (char)(i + 'A'));
            upper.put((char)(i + 'A'), (char)(i + 'a'));
        }
    }

    // compair both string is equal or not
    boolean isCompair(String s1, String s2){
        int n = s1.length();
        int m = s2.length();

        if(n != m) return false;

        for(int i = 0; i < n; i++){
            if(s1.charAt(i) != s2.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    String isReverse(String s){
        int n = s.length();
        String ans = "";

        for(int i = n-1; i >= 0; i--){
            ans += s.charAt(i);
        }

        return ans;
    }

    String lowerToUpperAndViceVersa(String s){
        int n = s.length();
        String ans = "";

        for(int i = 0; i < n; i++){
            char ch = s.charAt(i) ;
            
            if(ch >= 67 && ch <= 90) {
                ans += (char)(ch+32);
            } else if(ch >= 97 && ch <= 121) {
                ans += (char)(ch-32);
            }
        }

        return ans;
    }

    String isLargestWord(String s){
        String word = "";
        int m = s.length();
        int len = 0;
        String largeWord = "";

        for(int i = 0; i < m; i++){
            char ch = s.charAt(i);
            if(ch == ' ') {
                int n = word.length();
                if(n > len){
                    len = n;
                    largeWord = word;
                }
                word = "";
                continue;
            }
            word += ch;
            
            //for the last word
            if(i == m-1) {
                if(word.length() > len){
                    largeWord = word;
                }
            }
        }

        return largeWord;
    }
}

class OperationsOfString{
    public static void main(String[] args){
        String s1 = "Hello";
        String s2 = "World";

        Operations op = new Operations();
        System.out.println("Both Strings are equals : "+op.isCompair(s1, s2));
        System.out.println(s1+" Reverse is : "+op.isReverse(s1));
        System.out.println(s1+" lowercase characters with uppercase and vice-versa : "+op.lowerToUpperAndViceVersa(s1));
        System.out.println("Largaest Word inside a string : "+op.isLargestWord("My Name is Prateek Khandelwal"));
    }
}