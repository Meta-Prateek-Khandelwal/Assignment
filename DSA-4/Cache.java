import java.util.HashMap;

class Cache{

    static int uniqueStringCheck(String str, HashMap<String, Integer> uniqueString){
        int len = str.length();
        HashMap<Character, Integer> uniqueChar = new HashMap<>();
        if(!uniqueString.containsKey(str)){
            
            for(char ch: str.toCharArray()){
                if(!uniqueChar.containsKey(ch)){
                    uniqueChar.put(ch, 1);
                }
            }
            uniqueString.put(str, uniqueChar.size());
        }
        return uniqueChar.get(str);
    }
    public static void main(String[] args) {
        HashMap<String, Integer> uniqueString = new HashMap<>();
        System.out.println(uniqueStringCheck("Hello", uniqueString));
        System.out.println(uniqueStringCheck("World", uniqueString));
        System.out.println(uniqueStringCheck("Hello", uniqueString));
    }
}