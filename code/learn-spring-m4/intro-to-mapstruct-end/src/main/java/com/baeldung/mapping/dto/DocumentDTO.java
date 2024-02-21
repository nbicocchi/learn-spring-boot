package com.baeldung.mapping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DocumentDTO {
    private int id;
    private String title;
    private String text;
    private List<String> comments;
    private String author;
}
