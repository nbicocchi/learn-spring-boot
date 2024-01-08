package com.baeldung.mapping.mapper;

import com.baeldung.mapping.dto.DivisionDTO;
import com.baeldung.mapping.entity.Division;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DivisionMapper {
    DivisionDTO divisionToDivisionDTO(Division entity);

    Division divisionDTOToDivision(DivisionDTO dto);
}
