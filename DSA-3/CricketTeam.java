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
    ArrayList<Bowler> bowlers;
    int capacity;
    int totalBall;

    PriorityQueue(int capacity){
        this.capacity = capacity;
        bowlers = new ArrayList<>();
        totalBall = 0;
    }

    public void enqueue(Bowler bowler) {
        int idx = 0;

        if(isFull()){
            System.out.println("Queue is OverFlow.");
            return ;
        }
       
        while(idx < bowlers.size() && bowler.balls <= bowlers.get(idx).balls){
            idx++;
        }
        bowlers.add(idx, bowler);
        totalBall += bowler.balls;
    }

    public Bowler dequeue() {
        if(isEmpty()){
            System.out.println("Queue is Underflow.");
            return null;
        }
        totalBall -= bowlers.get(0).balls;
        return bowlers.remove(0);
    }

    public Bowler isPeek() {
        if(isEmpty()){
            System.out.println("Queue is Underflow.");
            return null;
        }
        return bowlers.get(0);
    }

    public boolean isFull() {
        return bowlers.size() == capacity;
    }

    public boolean isEmpty() {
        return bowlers.size() == 0;
    }
    
    void display(){
        for(int idx = 0; idx < bowlers.size(); idx++){
            System.out.print(bowlers.get(idx).name+" -> ");
        }
    }
}


public class CricketTeam {
    
    static void play(int playBall, PriorityQueue pq){
        while(playBall > 0){    
            Bowler bowler = pq.dequeue();
            if(bowler == null) break;

            System.out.print(bowler.name+ " -> ");
            bowler.balls--;
            if(bowler.balls > 0) {
                pq.enqueue(bowler);
                // pq.display();
            }        
            playBall--;
        }
        System.out.println("Finish Game");
    }

    public static void main(String[] args) {        
        PriorityQueue pq = new PriorityQueue(4);
        pq.enqueue(new Bowler("B1", 1));
        pq.enqueue(new Bowler("B2", 2));
        pq.enqueue(new Bowler("B3", 3));
        pq.enqueue(new Bowler("B4", 4));
        System.out.println(pq.totalBall);
        play(5, pq);
        
    }
}
