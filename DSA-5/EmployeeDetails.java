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

    Node insertionSort(Node head){
        if(head == null) {
            return head;
        }
        
        Node dummy = new Node(null);
        Node prevNode = dummy;
        Node currNode = head;
        Node nextNode ;

        while(currNode != null){
            nextNode = currNode.next;

            while(prevNode.next != null && ((prevNode.next.employee.salary >= currNode.employee.salary))){
                if(prevNode.next.employee.salary == currNode.employee.salary){
                    if(prevNode.next.employee.age < currNode.employee.age){
                        prevNode = prevNode.next;
                        continue;
                    }
                    break;
                }
                prevNode = prevNode.next;
            }

            currNode.next = prevNode.next;
            prevNode.next = currNode;
            prevNode = dummy;
            currNode = nextNode;
        }

        return dummy.next;
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
        Node newhead = sort.insertionSort(head);

        while(newhead != null){
            System.out.println(newhead.employee.name+"   "+newhead.employee.salary+" "+newhead.employee.age);
            newhead = newhead.next;
        }
    }
}
