class Node{
    Employee employee;
    Node next;

    Node(Employee employee){
        this.employee = employee;
        next = null;
    }
}

class Employee{
    String name;
    int id;
    int age;
    double salary;

    public Employee(String name, int id, int age, double salary) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.salary = salary;
    }
}

class Sort{

    Node quickSort(Node head){
        if(head == null || head.next == null) {
            return head;
        }
        
        Node mid = getMid(head);
        Node right = mid.next;
        mid.next = null;

        Node left = quickSort(head);
        right = quickSort(right);

        return merge(left, right);
    }

    private Node merge(Node leftHead, Node rightHead) {
        Node dummyNode = new Node(null);
        Node temp = dummyNode;

        while(leftHead != null && rightHead != null){
            if(leftHead.employee.salary > rightHead.employee.salary || 
                (leftHead.employee.salary == rightHead.employee.salary && 
                leftHead.employee.age <= rightHead.employee.age)){
                temp.next = leftHead;
                leftHead = leftHead.next;
            }else{
                temp.next = rightHead;
                rightHead = rightHead.next;
            }
            temp = temp.next;
        }

        temp.next = (leftHead != null) ? leftHead : rightHead;
        return dummyNode.next;
    }

    private Node getMid(Node head) {
        Node slow = head;
        Node fast = head.next;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}

public class EmployeeDetails {
    public static void main(String[] args) {
        Employee e1 = new Employee("Ram", 1, 21, 50000);
        Employee e2 = new Employee("Mohan", 2, 22, 35000);
        Employee e3 = new Employee("Sohan", 3, 25, 42000);
        Employee e4 = new Employee("Raman", 4, 23, 50000);
        Employee e5 = new Employee("Rakesh", 5, 29, 35000);
        Employee e6 = new Employee("Simit", 6, 22, 42000);

        Node node1 = new Node(e1);
        Node node2 = new Node(e2);
        Node node3 = new Node(e3);
        Node node4 = new Node(e4);
        Node node5 = new Node(e5);
        Node node6 = new Node(e6);

        Node head = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = null;
        
        Sort sort = new Sort();
        Node newhead = sort.quickSort(head);

        while(newhead != null){
            System.out.println(newhead.employee.name+"   "+newhead.employee.salary+" "+newhead.employee.age);
            newhead = newhead.next;
        }
    }
}
