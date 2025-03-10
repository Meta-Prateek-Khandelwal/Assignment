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
            if(s1.charAt(i) != s2.charAt(i)) return false;
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

    String isLowerUpper(String s){
        int n = s.length();
        String ans = "";

        for(int i = 0; i < n; i++){
            if(lower.containsKey(s.charAt(i))) ans += lower.get(s.charAt(i));
            else ans += upper.get(s.charAt(i));
        }

        return ans;
    }

    String isLargestWord(String s){
        ArrayList<String> words = new ArrayList<>();
        String word = "";
        int m = s.length();
        
        for(int i = 0; i < m; i++){
            char ch = s.charAt(i);
            if(ch == ' ') {
                words.add(word);
                word = "";
                continue;
            }
            word += ch;
            
            if(i == m-1) words.add(word);//for the last word
        }
        
        int len = 0;
        String large = "";

        for(String str: words){
            int n = str.length();
            if(n > len){
                len = n;
                large = str;
            }
        }

        return large;
    }
}

class OperationsOfString{
    public static void main(String[] args){
        String s1 = "Hello";
        String s2 = "World";

        Operations op = new Operations();
        System.out.println("Both Strings are equals : "+op.isCompair(s1, s2));
        System.out.println(s1+" Reverse is : "+op.isReverse(s1));
        System.out.println(s1+" lowercase characters with uppercase and vice-versa : "+op.isLowerUpper(s1));
        System.out.println("Largaest Word inside a string : "+op.isLargestWord("My Name is Prateek Khandelwal"));
    }
}