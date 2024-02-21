package com.baeldung.mapping.mapper;

import com.baeldung.mapping.dto.DocumentDTO;
import com.baeldung.mapping.entity.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class DocumentMapperTest {
    @Autowired
    DocumentMapper mapper;

    @Test
    void documentoToDocumentDTO() throws ParseException {
        Document entity = new Document(1, "a", "b", new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-01"));
        DocumentDTO dto = mapper.documentToDocumentDTO(entity);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getTitle(), entity.getTitle());
        assertEquals(dto.getText(), entity.getText());
        assertNull(dto.getComments());
        assertNull(dto.getAuthor());
    }
}