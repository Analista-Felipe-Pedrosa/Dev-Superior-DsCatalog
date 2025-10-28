package com.pedrosa.dscatalog.services;

import com.pedrosa.dscatalog.dto.CategoryDto;
import com.pedrosa.dscatalog.entities.Category;
import com.pedrosa.dscatalog.exceptions.EntityNotFoundException;
import com.pedrosa.dscatalog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional
    public List<CategoryDto> findAll(){
        List<Category> list = repository.findAll();
        return list.stream().map(x -> new CategoryDto(x)).collect(Collectors.toList());
    }

    public CategoryDto findById(Long id) {
        return new CategoryDto(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dado não encontrado")));
    }
}
