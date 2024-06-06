package ra.demo_employee_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.demo_employee_api.model.dto.request.EmployeeRequest;
import ra.demo_employee_api.model.entity.Employee;
import ra.demo_employee_api.service.IEmployeeService;

import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/{empId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer empId) {
        return new ResponseEntity<>(employeeService.getEmployeeById(empId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> insertEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.insertEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.updateEmployee(employee), HttpStatus.OK);
    }

    @DeleteMapping("/{empId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer empId) {
        employeeService.deleteEmployee(empId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<List<Employee>> getEmployeeByName(@PathVariable String name) {
        return new ResponseEntity<>(employeeService.getEmployeesByName(name), HttpStatus.OK);
    }

    @GetMapping("/salary")
    public ResponseEntity<List<Employee>> getEmployeeSalary(@RequestParam(value = "minSalary", defaultValue = "0") Double minSalary,
                                                            @RequestParam(value = "maxSalary", defaultValue = "0" ) Double maxSalary) {
        return new ResponseEntity<>(employeeService.getEmployeesBySalaryIsBetween(minSalary, maxSalary),HttpStatus.OK);
    }

    @GetMapping("/searchByName")
    public ResponseEntity<List<Employee>> searchEmployeeByName(@RequestBody EmployeeRequest employeeRequest) {
        List<Employee> employeeList = employeeService.getEmployeesByFullNameAndOrderBySalary(employeeRequest.getFullName(),employeeRequest.getPage()-1,employeeRequest.getSize());
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @GetMapping("/getTop10Salary")
    public ResponseEntity<List<Employee>> getTop10Employees() {
        return new ResponseEntity<>(employeeService.getTopBySalary(), HttpStatus.OK);
    }
}
