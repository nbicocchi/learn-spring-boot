package com.baeldung.mapping.mapper;

import com.baeldung.mapping.dto.DivisionDTO;
import com.baeldung.mapping.entity.Division;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class DivisionMapperTest {
    @Autowired
    DivisionMapper mapper;

    @Test
    void divisionToDivisionDTO() {
        Division entity = new Division(1, "John");
        DivisionDTO dto = mapper.divisionToDivisionDTO(entity);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getName(), entity.getName());
    }

    @Test
    void divisionDTOToDivision() {
    }
}