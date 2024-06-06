package ra.demo_employee_api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.demo_employee_api.model.entity.Employee;
import ra.demo_employee_api.repository.EmployeeRepository;
import ra.demo_employee_api.service.IEmployeeService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElseThrow(()->new NoSuchElementException("Khong ton tai nhan vien nay"));
    }

    @Override
    public Employee insertEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        employeeRepository.findById(employee.getEmpId()).orElseThrow(()->new NoSuchElementException("Khong ton tai nhan vien nay"));
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getEmployeesByName(String fullName) {
        return employeeRepository.getEmployeesByFullName(fullName);
    }

    @Override
    public List<Employee> getEmployeesBySalaryIsBetween(Double minSalary, Double maxSalary) {
        return employeeRepository.getEmployeesBySalaryBetween(minSalary, maxSalary);
    }

    @Override
    public List<Employee> getEmployeesByFullNameAndOrderBySalary(String fullName, Integer page, Integer itemPage) {
        Pageable pageable = PageRequest.of(page,itemPage);
        if (fullName == null || fullName.isEmpty()) {
            return employeeRepository.findAll(pageable).getContent();
        }else {
            return employeeRepository.getEmployeesByFullNameAndOrderBySalary(fullName,pageable);
        }
    }

    @Override
    public List<Employee> getTopBySalary() {
        return employeeRepository.getTopBySalary();
    }
}
