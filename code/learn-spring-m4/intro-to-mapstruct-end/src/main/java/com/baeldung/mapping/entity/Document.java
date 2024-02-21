package com.baeldung.mapping.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Document {
    private int id;
    private String title;
    private String text;
    private Date modificationTime;
}
