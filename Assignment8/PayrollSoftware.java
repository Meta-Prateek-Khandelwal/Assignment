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

class Developer extends Employee {
    private double bonus;

    Developer(String id, String name, double salary) {
        super(id, name, salary);
        this.department = "Developer";
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

class Tester extends Employee {
    private double bonus;

    Tester(String id, String name, double salary) {
        super(id, name, salary);
        this.bonus = (salary * 5) / 100;
        this.department = "Tester";
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
    protected ArrayList<Employee> departmentList;

    Department() {
        departmentList = new ArrayList<>();
    }

    boolean join(Employee e) {
        return departmentList.add(e);
    }

    boolean relieve(Employee e) {
        return departmentList.remove(e);
    }

    List<Employee> getEmployees() {
        return departmentList;
    }
}

class Organization {
    ArrayList<Department> organizationList;
    ArrayList<Employee> employeesList;

    Organization() {
        organizationList = new ArrayList<>();
        employeesList = new ArrayList<>();
    }

    boolean addDepartment(Department dep) {
        return organizationList.add(dep);
    }

    List<Employee> getAllEmployees() {
        for (Department department : organizationList) {
            employeesList.addAll(department.getEmployees());
        }

        return employeesList;
    }
}

class Payroll {

    double tax(double Salary) {
        if (Salary < 1200000) {
            return 0;
        }

        return (Salary * 5) / 100;
    }

    void salaryslip(Organization organization) {

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

        Department department = new Department();
        department.join(developer1);
        department.join(developer2);

        Organization organization = new Organization();
        organization.addDepartment(department);

        Payroll payroll = new Payroll();
        payroll.salaryslip(organization);
    }
}
