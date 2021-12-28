package com.example.mapper;

import com.example.pojo.Department;
import com.example.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeMapper {
    // simulate database
    private static Map<Integer, Employee> employees;

    @Autowired
    private DepartmentMapper departmentMapper;

    static {
        employees = new HashMap<>();
        employees.put(1, new Employee(1, "Employee 1", "000001@gmail.com", 1, new Department(1, "Information System Department"), new Date()));
        employees.put(2, new Employee(2, "Employee 2", "000001@gmail.com", 1, new Department(1, "Information System Department"), new Date()));
        employees.put(3, new Employee(3, "Employee 3", "000001@gmail.com", 1, new Department(1, "Information System Department"), new Date()));
        employees.put(4, new Employee(4, "Employee 4", "000001@gmail.com", 0, new Department(3, "Human Resource Department"), new Date()));
        employees.put(5, new Employee(5, "Employee 5", "000001@gmail.com", 0, new Department(4, "Marketing Department"), new Date()));
    }

    private static Integer employeeId = 6;
    public void addEmployee(Employee employee){
        if(employee.getId() == null)
            employee.setId(employeeId++);

        employee.setDepartment(departmentMapper.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    public Collection<Employee> getAllEmployee(){
        return employees.values();
    }

    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }

    public void deleteEmployee(Integer id){
        employees.remove(id);
    }
}
