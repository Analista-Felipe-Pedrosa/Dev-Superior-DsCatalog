package com.pedrosa.dscatalog.dto;

import com.pedrosa.dscatalog.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Long id;
    private String name;

    public CategoryDto(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
