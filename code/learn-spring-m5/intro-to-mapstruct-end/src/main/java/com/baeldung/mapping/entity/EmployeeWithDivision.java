package com.baeldung.mapping.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeWithDivision {
    private int id;
    private String name;
    private Division division;
}