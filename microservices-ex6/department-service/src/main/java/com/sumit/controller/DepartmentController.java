package com.sumit.controller;

import com.sumit.client.EmployeeClient;
import com.sumit.model.Department;
import com.sumit.repository.DepartmentRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/dept")
public class DepartmentController {


    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private EmployeeClient employeeClient;



    @PostMapping("/save")
    public Department add(@RequestBody Department department){
        log.info("Adding the department {}",department);
        return departmentRepo.addDept(department);
    }

    @GetMapping("/getall")
    public List<Department> getAll(){
        log.info("fetching all department..");
        return departmentRepo.getDept();
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id){
        log.info("find by id = {}",id);
        return departmentRepo.findById(id);
    }


    @GetMapping("/with-emp")
    public List<Department> getAllWithEmployee(){
        log.info("fetching all department..");

        List<Department> departments=departmentRepo.getDept();
        System.out.println(employeeClient.getByDeptId(1L));
        departments.forEach(department ->
                department
                        .setEmployees(employeeClient.getByDeptId(department.getId()))
                );

        return departments;
    }
}
