package com.sumit.repository;

import com.sumit.model.Department;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentRepo {

    private List<Department> dept=new ArrayList<>();

    public Department addDept(Department department){
        dept.add(department);
        return department;
    }

    public Department findById(Long id){
        return dept.stream()
                .filter(d->d.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public List<Department> getDept() {

        return dept;
    }
}
