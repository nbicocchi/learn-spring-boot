package com.baeldung.mapping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeWithDateDTO {
    private int employeeId;
    private String employeeName;
    private String date;
}