import java.util.LinkedList;

class Node{
    int data;
    Node next;

    Node(int data){
        this.data = data;
        this.next = null;
    }
}
public class LoopCheck {
    public static void main(String[] args) {
        Node node1 = new Node(5);
        Node node2 = new Node(6);
        Node node3 = new Node(7);
        Node node4 = new Node(8);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        
        System.out.println(isLoop(node1));
    }

    static boolean isLoop(Node head){
        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) return true;
        }
        return false;
    }
}
