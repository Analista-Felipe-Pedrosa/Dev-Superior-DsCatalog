package com.pedrosa.dscatalog.dto;

import com.pedrosa.dscatalog.entities.Category;
import lombok.Data;

@Data
public class CategoryDto {

    private Long id;
    private String name;

    public CategoryDto(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
