class  Node{
    int data;
    Node next;
    Node(int data){
        this.data = data;
        this.next = null;
    }
}
public class RotateLinkedList {
    public static void main(String[] args) {
        Node head = new Node(2);
        Node node1 = new Node(3);
        Node node2 = new Node(4);
        Node node3 = new Node(5);
        Node node4 = new Node(6);
        Node node5 = new Node(7);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        int l = 2;
        int r = 5;
        int n = 2;
        
        Node Head = roatate(head, l, r, n);
        
        while(Head != null){
            System.out.print(Head.data + " ->" );
            Head = Head.next;
            if(Head == null) System.out.print("null");
        }
    }

    static Node roatate(Node head, int l, int r, int n){
        if(head == null || head.next == null || n == 0) return head;

        Node dummy = new Node(0);
        dummy.next = head;
        Node beforeL = dummy;

        for(int i = 0; i < (l-1); i++){
            beforeL = beforeL.next;
        }

        Node start = beforeL.next;
        Node end = start;
        
        for(int i = l; i < r; i++){
            end = end.next;
        }
        
        Node tempNode = start;
        int len = 1;
        while(tempNode != end){
            tempNode = tempNode.next;
            len++;
        }
        n %= len;
        if(n == 0) return head;
        n = len - n - 1;
        tempNode = start;
        Node lenNode = tempNode;
        
        while(n-- > 0){
            lenNode = lenNode.next;
        }
        beforeL.next = lenNode.next;
        lenNode.next = end.next;
        end.next = start;
        
        return dummy.next;
    }
}
