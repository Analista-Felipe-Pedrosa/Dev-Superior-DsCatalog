package com.pedrosa.dscatalog.services;

import com.pedrosa.dscatalog.dto.CategoryDto;
import com.pedrosa.dscatalog.entities.Category;
import com.pedrosa.dscatalog.exceptions.ControllerNotFoundException;
import com.pedrosa.dscatalog.exceptions.DatabaseException;
import com.pedrosa.dscatalog.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDto> findAll(){
        List<Category> list = repository.findAll();
        return list.stream().map(x -> new CategoryDto(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryDto findById(Long id) {
        return new CategoryDto(repository.findById(id)
                .orElseThrow(() -> new ControllerNotFoundException("Dado não encontrado")));
    }

    @Transactional
    public CategoryDto insert(CategoryDto dto) {

        Category category = new Category();
        category.setName(dto.getName());
        category = repository.save(category);
        return new CategoryDto(category);
    }

    @Transactional
    public CategoryDto update(Long id,CategoryDto dto) {
        try {
            Category entity = repository.getReferenceById(id);
            entity.setName(dto.getName());
            entity = repository.save(entity);
            return new CategoryDto(entity);
        }catch (EntityNotFoundException e){
            throw new ControllerNotFoundException("Registro não encontrado para o Id : " + id);
        }
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ControllerNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }
}
