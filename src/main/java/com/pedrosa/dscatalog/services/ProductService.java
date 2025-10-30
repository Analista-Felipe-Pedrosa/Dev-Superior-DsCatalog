package com.pedrosa.dscatalog.services;

import com.pedrosa.dscatalog.dto.CategoryDto;
import com.pedrosa.dscatalog.dto.ProductDto;
import com.pedrosa.dscatalog.entities.Category;
import com.pedrosa.dscatalog.entities.Product;
import com.pedrosa.dscatalog.exceptions.ControllerNotFoundException;
import com.pedrosa.dscatalog.exceptions.DatabaseException;
import com.pedrosa.dscatalog.repository.CategoryRepository;
import com.pedrosa.dscatalog.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<ProductDto> findAll(Pageable pageable){
        Page<Product> list = repository.findAll(pageable);
        return list.map(x -> new ProductDto(x));
    }

    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        Product entity = obj.orElseThrow(() -> new ControllerNotFoundException("Dado não encontrado"));
        return new ProductDto(entity, entity.getCategories());
    }

    @Transactional
    public ProductDto insert(ProductDto dto) {

        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDto(entity, entity.getCategories());
    }

    @Transactional
    public ProductDto update(Long id,ProductDto dto) {
        try {
            Product entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            return new ProductDto(entity, entity.getCategories());
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
    private void copyDtoToEntity(ProductDto dto, Product entity) {
          entity.setName(dto.getName());
          entity.setPrice(dto.getPrice());
          entity.setDate(dto.getDate());
          entity.setImgUrl(dto.getImgUrl());
          entity.setDescription(dto.getDescription());

          entity.getCategories().clear();

          for(CategoryDto catDto : dto.getCategories()){
              Category cat = categoryRepository.getReferenceById(catDto.getId());
              entity.getCategories().add(cat);
          }
    }
}
