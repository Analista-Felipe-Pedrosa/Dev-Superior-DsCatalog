package com.pedrosa.dscatalog.services;

import com.pedrosa.dscatalog.entities.Category;
import com.pedrosa.dscatalog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional
    public List<Category> findAll(){
        return repository.findAll();
    }
}
