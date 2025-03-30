package Assignment8;

import java.util.ArrayList;
import java.util.List;

abstract class Employee {
    protected String employeeId;
    protected String employeeName;
    protected String department;
    protected double salary;

    Employee(String id, String name, double salary) {
        this.employeeId = id;
        this.employeeName = name;
        this.salary = salary;
    }

    String getId() {
        return employeeId;
    }

    String getName() {
        return employeeName;
    }

    String getDepartment() {
        return department;
    }

    abstract double getBasicSalary();

    abstract double getBonus();

    abstract double getCompensation();
}

enum DepartmentName {
    IT,
    NON_IT
}

class Developer extends Employee {
    private double bonus;

    Developer(String id, String name, double salary) {
        super(id, name, salary);
        this.department = DepartmentName.IT.toString();
        this.bonus = (salary * 7) / 100;
    }

    @Override
    double getBasicSalary() {
        return salary - bonus;
    }

    @Override
    double getBonus() {
        return bonus;
    }

    @Override
    double getCompensation() {
        return salary;
    }
}

class HR extends Employee {
    private double bonus;

    HR(String id, String name, double salary) {
        super(id, name, salary);
        this.bonus = (salary * 5) / 100;
        this.department = DepartmentName.NON_IT.toString();
    }

    @Override
    double getBasicSalary() {
        return salary - bonus;
    }

    @Override
    double getBonus() {
        return bonus;
    }

    @Override
    double getCompensation() {
        return salary;
    }
}



class Department {
    static private ArrayList<Employee> employeeList;
    DepartmentName departmentName;

    Department(DepartmentName departmentName) {
        employeeList = new ArrayList<>();
        this.departmentName = departmentName;
    }

    boolean join(Employee e) {
        return employeeList.add(e);
    }

    boolean relieve(Employee e) {
        return employeeList.remove(e);
    }

    static List<Employee> getEmployees() {
        return employeeList;
    }
}

class Organization {
    ArrayList<Department> departments;

    Organization() {
        departments = new ArrayList<>();
    }

    boolean addDepartment(Department dep) {
        return departments.add(dep);
    }

    List<Employee> getAllEmployees() {
        return Department.getEmployees();
    }
}

class Payroll {

    double tax(double salary) {
        if (salary < 1200000) {
            return 0;
        }

        return (salary * 5) / 100;
    }

    void salarySlip(Organization organization) {

        for (Employee employee : organization.getAllEmployees()) {
            System.out.println("Employee id  : " + employee.getId());
            System.out.println("Employee Name : " + employee.getName());
            System.out.println("Employee department : " + employee.getDepartment());
            System.out.println("Employee Net Salary : " + (employee.getCompensation() - tax(employee.getCompensation())));
            System.out.println("Employee Tax : " + tax(employee.getCompensation()));
            System.out.println();
        }
    }
}

public class PayrollSoftware {
    public static void main(String[] args) {
        Developer developer1 = new Developer("E25/2641", "Ajay", 520000);
        Developer developer2 = new Developer("G25/0413", "Ram", 1300000);
        HR hr1 = new HR("H25/0021", "Akshit", 50000);

        Department departmentDev = new Department(DepartmentName.IT);
        Department departmentHr = new Department(DepartmentName.NON_IT);
        departmentDev.join(developer1);
        departmentDev.join(developer2);
        departmentHr.join(hr1);

        Organization organization = new Organization();
        organization.addDepartment(departmentDev);
        organization.addDepartment(departmentHr);

        Payroll payroll = new Payroll();
        payroll.salarySlip(organization);
    }
}
