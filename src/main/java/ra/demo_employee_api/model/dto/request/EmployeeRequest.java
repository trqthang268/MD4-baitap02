package ra.demo_employee_api.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeRequest {
    private String fullName;
    private Integer page;
    private Integer size;
}
