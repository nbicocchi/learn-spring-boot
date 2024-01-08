package com.baeldung.mapping.mapper;

import com.baeldung.mapping.dto.EmployeeDTO;
import com.baeldung.mapping.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(target = "employeeId", source = "entity.id")
    @Mapping(target = "employeeName", source = "entity.name")
    EmployeeDTO employeeToEmployeeDTO(Employee entity);

    @Mapping(target = "id", source = "dto.employeeId")
    @Mapping(target = "name", source = "dto.employeeName")
    Employee employeeDTOtoEmployee(EmployeeDTO dto);
}
