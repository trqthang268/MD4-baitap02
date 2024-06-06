package ra.demo_employee_api.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ra.demo_employee_api.model.entity.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>, PagingAndSortingRepository<Employee,Integer> {
    @Query("select e from Employee e where e.FullName like concat('%',:fullName,'%')")
    public List<Employee> getEmployeesByFullName(String fullName);

    //-	Get employees with salary between minSalary to maxSalary
    // (tìm các nhân viên có mức lương trong khoảng từ min đến max)
    @Query("select e from Employee e where e.Salary between :minSalary and :maxSalary")
    public List<Employee> getEmployeesBySalaryBetween(Double minSalary, Double maxSalary);

    //-	Get employees by name with paging and sorting descending by salary
    // (tìm kiếm các nhân viên theo tên, phân trang và sắp xếp giảm dần theo lương)
    @Query("select e from Employee e where e.FullName like concat('%',:fullName,'%') order by e.Salary desc ")
    public List<Employee> getEmployeesByFullNameAndOrderBySalary(String fullName, Pageable pageable);

    //-	Get top 10 employees whose have greatest salary
    // (Tìm 10 nhân viên có mức lương cao nhất)
    @Query("select  e from Employee e order by e.Salary desc limit 10")
    public List<Employee> getTopBySalary();
}
