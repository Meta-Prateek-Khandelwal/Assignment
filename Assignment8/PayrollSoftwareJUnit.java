package Assignment8;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Test;

public class PayrollSoftwareJUnit {
    Organization  organization = new Organization();
    Department department = new Department(DepartmentName.IT);
    Payroll payroll = new Payroll();
    Developer developer1;
    Developer developer2;
    Developer developer3;
    
    @Test
    public void testEmployeeAddition(){
        developer1 = new Developer("E25/2642", "Ajay", 520000);
        developer2 = new Developer("G25/0413", "Arun", 1300000);
        developer3 = new Developer("G25/0412", "Vrun", 1300000);

        department.join(developer1);
        department.join(developer2);
        organization.addDepartment(department);
        
        assertEquals(2, Department.getEmployees().size());
        assertTrue(Department.getEmployees().contains(developer1));
        assertTrue(Department.getEmployees().contains(developer2));
        assertEquals(2, organization.getAllEmployees().size());
    }
}
