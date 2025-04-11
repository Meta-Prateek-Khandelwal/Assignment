interface Priority{
    void enqueue(int item);
    int dequeue();
    int peek();
    boolean isFull();
    boolean isEmpty();
}

class PriorityQueue implements Priority{
    int[] pqArray;
    int rear;
    int front;
    int capacity;
    int size ;

    PriorityQueue(int capacity){
        this.capacity = capacity;
        pqArray = new int[capacity+1];
        rear = 0;
        size = 0;
    }

    @Override
    public void enqueue(int item) {
        int idx = 0;

        if(isFull()){
            System.out.println("Queue is OverFlow.");
            return ;
        }
        if(rear == 0){
            pqArray[rear] = item;
        }else{
            for(idx = rear-1; idx >= 0; idx--){
                if(item < pqArray[idx]){
                    pqArray[idx+1] = pqArray[idx];
                }else{
                    break;
                }
            }
            pqArray[idx + 1] = item;
        }
        rear++;
        size++;
    }

    @Override
    public int dequeue() {
        if(isEmpty()){
            System.out.println("Queue is Underflow.");
            return -1;
        }
        size--;
        return pqArray[front++];
    }

    @Override
    public int peek() {
        if(isEmpty()){
            System.out.println("Queue is Underflow.");
            return -1;
        }
        return pqArray[front];
    }

    @Override
    public boolean isFull() {
        if(size == capacity) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        return false;
    }
    
    void display(){
        for(int idx = 0; idx < size; idx++){
            if(idx == size-1) {
                System.out.println(pqArray[idx]);
                return ;
            }
            System.out.print(pqArray[idx]+" -> ");
            
        }
    }
}

public class PriorityQueueImplement {
    public static void main(String[] args) {
        PriorityQueue pq = new PriorityQueue(5);
        pq.enqueue(2);
        pq.enqueue(4);
        pq.enqueue(1);
        pq.enqueue(5);
        pq.enqueue(3);
        pq.display();
    }
}
