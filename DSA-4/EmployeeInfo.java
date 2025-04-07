import java.util.*;

class Employee{
    int empId;
    String name;
    String address;

    Employee(int empId, String name, String address){
        this.empId = empId;
        this.name = name;
        this.address = address;
    }
}

class Operation{
    private ArrayList<Employee> empList;
    private HashSet<Integer> empIdSet;
    Operation(){
        empIdSet = new HashSet<>();
        empList = new ArrayList<>();
    }

    void sorting(){
        empList.sort(Comparator.comparingInt(e -> e.empId));
    }

    void sortingName(){
        empList.sort(Comparator.comparing(e -> e.name));
    }

    void removeDuplicate(Employee employee){
        if(!empIdSet.contains(employee.empId)){
            empList.add(employee);
            empIdSet.put(employee.empId);
        }
    }

    void dispaly(){
        for(Employee emp : empList){
            System.out.println(emp.empId+" "+emp.name);
        }
    }
}

public class EmployeeInfo {
    public static void main(String[] args) {
        Operation operation = new Operation();
        
        Employee employee1 = new Employee(1, "Ram", "jaipur");
        operation.removeDuplicate(employee1);

        Employee employee2 = new Employee(1, "Ram", "jaipur");
        operation.removeDuplicate(employee2);

        Employee employee3 = new Employee(2, "Ajay", "jaipur");
        operation.removeDuplicate(employee3);

        Employee employee4 = new Employee(5, "Mahesh", "jKota");
        operation.removeDuplicate(employee4);

        Employee employee5 = new Employee(2, "Ram", "jaipur");
        operation.removeDuplicate(employee5);
        
        Employee employee6 = new Employee(3, "Rakesh", "jKota");
        operation.removeDuplicate(employee6);
        operation.dispaly();
        
        operation.sorting();
        operation.dispaly();

        operation.sortingName();
        operation.dispaly();
    }
}
