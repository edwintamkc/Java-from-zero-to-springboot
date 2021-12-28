package com.example.mapper;

import com.example.pojo.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DepartmentMapper {
    // simulate database
    private static Map<Integer, Department> departments;

    static {
        departments = new HashMap<>();
        departments.put(1, new Department(1, "Information System Department"));
        departments.put(2, new Department(2, "Accounting Department"));
        departments.put(3, new Department(3, "Human Resource Department"));
        departments.put(4, new Department(4, "Marketing Department"));
        departments.put(5, new Department(5, "General Department"));
    }

    public Collection<Department> getDepartments(){
        return departments.values();
    }

    public Department getDepartmentById(Integer id){
        return departments.get(id);
    }
}
