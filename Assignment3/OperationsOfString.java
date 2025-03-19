package Assignment3;
import java.util.*;

class Operations{
    /**
     * compair both string is equal or not
     * @param the first is string
     * @param the second is string
     * @return boolean
     */
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

    /**
     * reverse the string
     * @param the string
     * @return string
     */
    String reverse(String s){
        int n = s.length();
        String reverseString = "";

        for(int i = n-1; i >= 0; i--){
            reverseString += s.charAt(i);
        }

        return reverseString;
    }

    /**
     * lower character change to upper and upper character change to lower
     * @param the string
     * @return string
     */
    String lowerToUpperAndViceVersa(String s){
        int n = s.length();
        String swapped = "";

        for(int i = 0; i < n; i++){
            char ch = s.charAt(i) ;
            
            if(ch >= 67 && ch <= 90) {
                swapped += (char)(ch+32);
            } else if(ch >= 97 && ch <= 121) {
                swapped += (char)(ch-32);
            }
        }

        return swapped;
    }

    /**
     * largest word in a string
     * @param the string
     * @return string
     */
    String largestWord(String s){
        String word = "";
        int m = s.length();
        int len = 0;
        String largeWord = "";

        for(int i = 0; i < m; i++){
            char ch = s.charAt(i);
            if(ch == ' ') {
                int n = word.length();
                if(n >= len){
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
        System.out.println(s1+" Reverse is : "+op.reverse(s1));
        System.out.println(s1+" lowercase characters with uppercase and vice-versa : "+op.lowerToUpperAndViceVersa(s1));
        System.out.println("Largaest Word inside a string : "+op.largestWord("My Name is Prateek Khandelwal"));
    }
}