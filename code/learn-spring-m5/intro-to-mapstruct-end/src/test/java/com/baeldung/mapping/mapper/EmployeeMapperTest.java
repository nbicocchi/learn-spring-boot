package com.baeldung.mapping.mapper;

import com.baeldung.mapping.dto.EmployeeDTO;
import com.baeldung.mapping.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class EmployeeMapperTest {
    @Autowired
    EmployeeMapper mapper;

    @Test
    void employeeToEmployeeDTO() {
        Employee entity = new Employee(1, "John");
        EmployeeDTO dto = mapper.employeeToEmployeeDTO(entity);

        assertEquals(dto.getEmployeeId(), entity.getId());
        assertEquals(dto.getEmployeeName(), entity.getName());
    }

    @Test
    void employeeDTOtoEmployee() {
        EmployeeDTO dto = new EmployeeDTO(1, "John");
        Employee entity = mapper.employeeDTOtoEmployee(dto);

        assertEquals(dto.getEmployeeId(), entity.getId());
        assertEquals(dto.getEmployeeName(), entity.getName());
    }
}