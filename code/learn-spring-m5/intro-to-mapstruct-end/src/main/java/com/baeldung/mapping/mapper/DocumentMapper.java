package com.baeldung.mapping.mapper;

import com.baeldung.mapping.dto.DocumentDTO;
import com.baeldung.mapping.entity.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "author", ignore = true)
    DocumentDTO documentToDocumentDTO(Document entity);

    @Mapping(target = "modificationTime", ignore = true)
    Document documentDTOToDocument(DocumentDTO dto);
}
