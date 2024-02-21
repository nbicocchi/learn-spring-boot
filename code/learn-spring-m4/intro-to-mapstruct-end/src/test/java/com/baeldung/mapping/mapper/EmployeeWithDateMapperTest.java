package com.baeldung.mapping.mapper;

import com.baeldung.mapping.dto.EmployeeWithDateDTO;
import com.baeldung.mapping.entity.EmployeeWithDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class EmployeeWithDateMapperTest {
    @Autowired
    EmployeeWithDateMapper mapper;

    @Test
    void employeeWithDateToEmployeeWithDateDTO() throws ParseException {
        EmployeeWithDate entity = new EmployeeWithDate(1, "John", new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-01"));
        EmployeeWithDateDTO dto = mapper.employeeWithDateToEmployeeWithDateDTO(entity);

        assertEquals(dto.getEmployeeId(), entity.getId());
        assertEquals(dto.getEmployeeName(), entity.getName());
        assertEquals(dto.getDate(), "01-01-2000 00:00:00");
    }
}