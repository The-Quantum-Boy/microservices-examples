package com.sumit.controller;

import com.sumit.model.Employee;
import com.sumit.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/emp")
public class EmployeeCotroller {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/add")
    public Employee add(@RequestBody Employee employee){

        log.info("employee added {}",employee);
        return employeeRepository.add(employee);
    }

    @GetMapping("getall")
    public List<Employee> getall(){
        log.info("fetched all employees");
        return employeeRepository.findAll();
    }

    @GetMapping("/emp/{id}")
    public Employee getById(@PathVariable Long id){
        log.info("fetched employee by employee id {}",id);
        return employeeRepository.findById(id);
    }

    @GetMapping("/dept/{deptId}")
    public List<Employee> getByDeptId(@PathVariable Long deptId){
        log.info("fetched employees by departmentId {}",deptId);
        return employeeRepository.findByDeptId(deptId);
    }
}
