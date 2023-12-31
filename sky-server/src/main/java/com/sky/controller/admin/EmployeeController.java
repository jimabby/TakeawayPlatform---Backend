package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "Employee API")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value="Employee logs in")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation("Employee logs out")
    public Result<String> logout() {
        return Result.success();
    }

    @PostMapping
    @ApiOperation("Add Employee")
    public Result save(@RequestBody  EmployeeDTO employeeDTO){
        log.info("Add Employee: {}", employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success();
    }

    /**
     * Employee search page
     * @param employeePageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("Employee search page")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("Employee Search, parameter: {}", employeePageQueryDTO);
        PageResult pageResult= employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * Change the status of employee account
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("Change the status of employee account")
    public Result startOrStop(@PathVariable Integer status, Long id) {
        log.info("Change the status of employee account: {}, {}", status, id);
        employeeService.startOrStop(status, id);
        return Result.success();
    }

    /**
     * Search the Employee Information base on id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("Search the Employee Information base on id")
    public Result<Employee> getBy8Id(@PathVariable  Long id) {
        Employee employee = employeeService.getById(id);
        return Result.success(employee);
    }

    /**
     * Update Employee information
     * @param employeeDTO
     * @return
     */
    @PutMapping
    @ApiOperation("Update Employee information")
    public Result update(@RequestBody EmployeeDTO employeeDTO) {
        log.info("Update Employee Information: {}", employeeDTO);
        employeeService.update(employeeDTO);
        return Result.success();
    }

}
