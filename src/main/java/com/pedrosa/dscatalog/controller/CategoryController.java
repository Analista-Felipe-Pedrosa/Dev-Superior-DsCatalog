package com.pedrosa.dscatalog.controller;

import com.pedrosa.dscatalog.entities.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @GetMapping
    public List<Category> findAll(){
        return null;
    }
}
