package ra.demo_employee_api.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import ra.demo_employee_api.model.entity.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(Integer id);

    Employee insertEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(Integer id);

    List<Employee> getEmployeesByName(String fullName);

    List<Employee> getEmployeesBySalaryIsBetween(Double minSalary, Double maxSalary);

    List<Employee> getEmployeesByFullNameAndOrderBySalary(String fullName, Integer page,Integer itemPage);

    List<Employee> getTopBySalary();
}
