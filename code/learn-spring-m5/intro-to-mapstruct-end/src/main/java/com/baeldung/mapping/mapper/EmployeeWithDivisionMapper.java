package com.baeldung.mapping.mapper;

import com.baeldung.mapping.dto.EmployeeWithDivisionDTO;
import com.baeldung.mapping.entity.EmployeeWithDivision;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = DivisionMapper.class)
public interface EmployeeWithDivisionMapper {
    @Mapping(target = "employeeId", source = "entity.id")
    @Mapping(target = "employeeName", source = "entity.name")
    @Mapping(target = "divisionDTO", source = "entity.division")
    EmployeeWithDivisionDTO employeeToEmployeeDTO(EmployeeWithDivision entity);

    @Mapping(target = "id", source = "dto.employeeId")
    @Mapping(target = "name", source = "dto.employeeName")
    @Mapping(target = "division", source = "dto.divisionDTO")
    EmployeeWithDivision employeeDTOtoEmployee(EmployeeWithDivisionDTO dto);
}
