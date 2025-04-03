interface Queue {
    void enqueue(int item);
    int dequeue();
    boolean isEmpty();
    boolean isFull();
    int peek();
}

class ArrayQueue implements Queue{
    private int[] queue ;
    private int front, rear, capacity, size;

    ArrayQueue(int capacity){
        this.capacity = capacity;
        queue = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    @Override
    public void enqueue(int item) {
        if(isFull()){
            System.out.println("Queue is OverFlow.");
            return ;
        }

        rear = (rear + 1) % capacity; 
        queue[rear] = item;
        size++;
    }

    @Override
    public int dequeue() {
        if(isEmpty()){
            System.out.println("Queue is UnderFlow.");
            return -1;
        }
        int val = queue[front];
        queue[front] = -1;
        front = (front + 1) % capacity; 
        size--;
        return val;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0) return true;
        return false;
    }

    @Override
    public boolean isFull() {
        if(size == capacity) return true;
        return false;
    }

    @Override
    public int peek() {
        if(isEmpty()){
            System.out.println("Queue is UnderFlow.");
            return -1;
        }
        int val = queue[front];
        return val;
    }
    
    void display(){
        int temp = front;
        System.out.println(front +" "+ rear);
        while(temp != rear){
            System.out.print(queue[temp]+"->");
            temp = (temp+1) % capacity;
        }
        System.out.println(queue[temp]);
    }
}

public class ImplementOfQueue {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(5);
        // System.out.println(queue.isEmpty());
        queue.enqueue(5);
        queue.enqueue(10);
        queue.enqueue(15);
        queue.enqueue(20);
        queue.enqueue(25);
        // System.out.println(queue.dequeue());
        // System.out.println(queue.dequeue());
        // queue.enqueue(30);
        // queue.enqueue(35);
        // System.out.println(queue.isFull());
        queue.display();
    }
}
