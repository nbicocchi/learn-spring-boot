package com.baeldung.mapping.mapper;

import com.baeldung.mapping.dto.EmployeeWithDivisionDTO;
import com.baeldung.mapping.entity.Division;
import com.baeldung.mapping.entity.EmployeeWithDivision;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class EmployeeWithDivisionMapperTest {
    @Autowired
    EmployeeWithDivisionMapper mapper;

    @Test
    public void employeeWithDivisionToEmployeeWithDivisionDTO() {
        EmployeeWithDivision entity = new EmployeeWithDivision(1, "a", new Division(1, "Division1"));
        EmployeeWithDivisionDTO dto = mapper.employeeToEmployeeDTO(entity);
        assertEquals(dto.getDivisionDTO().getId(), entity.getDivision().getId());
        assertEquals(dto.getDivisionDTO().getName(), entity.getDivision().getName());
    }
}