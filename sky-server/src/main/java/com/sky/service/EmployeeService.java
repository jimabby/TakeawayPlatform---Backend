package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     *Add Employee
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * Employee Search page
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * Change the status of employee account
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * Search the Employee Information base on id
     * @param id
     * @return
     */
    Employee getById(Long id);

    /**
     * Update Employee information
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO);
}
