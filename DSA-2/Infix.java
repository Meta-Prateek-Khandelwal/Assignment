import java.util.ArrayList;

interface Stack{
    void push(int item);
    int pop();
    int peek();
    boolean isEmpty(); 
    void display();   
}

class StackImplement implements Stack{
    private ArrayList<Integer> token;
    private int top;

    StackImplement(){
        token = new ArrayList<>();
        top = -1;
    }

    @Override
    public void push(int item) {
        ++top;
        token.add(item);
    }

    @Override
    public int pop() {
        if(isEmpty()){
            System.out.println("Stack is Empty.");
            return -1;
        }
        int val = token.remove(top);
        top--;
        return val;
    }

    @Override
    public int peek() {
        if(isEmpty()){
            System.out.println("Stack is Empty.");
            return -1;
        }
        int val = token.get(top);
        return val;
    }

    @Override
    public boolean isEmpty() {
        if(token.size() == 0) return true;
        return false;
    }

    @Override
    public void display(){
        for(int i = 0; i < token.size(); i++){
            System.out.print(token.get(i)+" ");
        }
    }
}
public class Infix {
    public static void main(String[] args) {
        StackImplement stack = new StackImplement();
        // stack.pop();
        // stack.push(1);
        // stack.push(2); 
        // stack.push(3);
        // stack.push(4);
        // stack.push(5);
        // stack.push(6); 
        // System.out.println(stack.pop());
        // System.out.println(stack.peek());
        // stack.display();      
        String s = "2 * 3 + ( 6 + 7 ) * 3 + (10 / 2) <= 34";                                                                                                                                                                                             
    }
}
