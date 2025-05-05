import java.util.HashMap;
import java.util.HashSet;

class Cache{
    static private HashMap<String, Integer> uniqueString = new HashMap<>();

    static int uniqueStringCheck(String str){
        if(!uniqueString.containsKey(str)){
            int len = str.length();
        
            HashSet<Character> uniqueChar = new HashSet<>();
            
            for(char ch: str.toCharArray()){
                if(!uniqueChar.contains(ch)){
                    uniqueChar.add(ch);
                }
            }
            uniqueString.put(str, uniqueChar.size());
        }
        return uniqueString.get(str);
    }
    public static void main(String[] args) {
        
        System.out.println(uniqueStringCheck("Hello"));
        System.out.println(uniqueStringCheck("World"));
        System.out.println(uniqueStringCheck("Hello"));
    }
}