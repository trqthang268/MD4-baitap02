package ra.demo_employee_api.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer EmpId;
    private String FullName;
    private Boolean Gender;
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date Birthday;
    private String Address;
    private String Company;
    private String Department;
    @Min(value = 0)
    private Double Salary;

}
