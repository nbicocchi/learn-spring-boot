package com.baeldung.mapping.mapper;

import com.baeldung.mapping.dto.EmployeeWithDateDTO;
import com.baeldung.mapping.entity.EmployeeWithDate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeWithDateMapper {
    @Mapping(target = "employeeId", source = "entity.id")
    @Mapping(target = "employeeName", source = "entity.name")
    @Mapping(target = "date", source = "entity.date", dateFormat = "dd-MM-yyyy HH:mm:ss")
    EmployeeWithDateDTO employeeWithDateToEmployeeWithDateDTO(EmployeeWithDate entity);

    @Mapping(target = "id", source = "dto.employeeId")
    @Mapping(target = "name", source = "dto.employeeName")
    @Mapping(target = "date", source = "dto.date", dateFormat = "dd-MM-yyyy HH:mm:ss")
    EmployeeWithDate employeeWithDateDTOToEmployeeWithDate(EmployeeWithDateDTO dto);
}
