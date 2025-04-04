import java.util.ArrayList;

class Bowler{
    String name;
    int balls;
    Bowler(String name, int balls){
        this.name = name;
        this.balls = balls;
    }
}

class PriorityQueue {
    Bowler[] bowlers;
    int itemCount;
    int capacity;
    int size ;

    PriorityQueue(int capacity){
        this.capacity = capacity;
        bowlers = new Bowler[capacity+1];
        itemCount = 0;
        size = 0;
    }

    public void enqueue(Bowler bowler) {
        int idx = 0;

        if(isFull()){
            System.out.println("Queue is OverFlow.");
            return ;
        }
        if(itemCount == 0){
            bowlers[itemCount] = bowler;
        }else{
            for(idx = itemCount-1; idx >= 0; idx--){
                if(bowler.balls < bowlers[idx].balls){
                    bowlers[idx+1] = bowlers[idx];
                }else{
                    break;
                }
            }
            bowlers[idx + 1] = bowler;
        }
        itemCount++;
        size++;
    }

    public Bowler dequeue() {
        if(isEmpty()){
            System.out.println("Queue is Underflow.");
            return null;
        }
        size--;
        return bowlers[itemCount++];
    }

    public Bowler isPeek() {
        if(isEmpty()){
            System.out.println("Queue is Underflow.");
            return null;
        }
        return bowlers[itemCount-1];
    }

    public boolean isFull() {
        if(size == capacity) {
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        return false;
    }
    
    // void display(){
    //     for(int idx = 0; idx < size; idx++){
    //         if(idx == size-1) {
    //             System.out.println(pqArray[idx]);
    //             return ;
    //         }
    //         System.out.print(pqArray[idx]+" -> ");
            
    //     }
    // }
}


public class CricketTeam {
    
    static void play(int playBall, PriorityQueue pq){
       
        while(playBall > 0){    
            Bowler bowler = pq.dequeue();
            if(bowler == null) break;
            
            System.out.println(bowler.name);
            bowler.balls--;
            if(bowler.balls > 0) {
                pq.enqueue(bowler);
            }        
            playBall--;
        }
    }

    public static void main(String[] args) {
        int totalBall = 30;
        
        PriorityQueue pq = new PriorityQueue(4);
        pq.enqueue(new Bowler("B1", 8));
        pq.enqueue(new Bowler("B2", 5));
        pq.enqueue(new Bowler("B3", 9));
        pq.enqueue(new Bowler("B4", 3));
        play(3, pq);
    }
}
