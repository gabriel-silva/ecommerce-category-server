package br.com.ecommerce.service;

import br.com.ecommerce.exception.NotFoundException;
import br.com.ecommerce.model.Category;
import br.com.ecommerce.repository.CategoryRepository;
import br.com.ecommerce.validation.CategoryValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category save(@Valid CategoryValidation categoryValidation) {
        Category category = new Category();
        category.setName(categoryValidation.getName());
        return categoryRepository.save(category);
    }

    public Category update(Long id, @Valid CategoryValidation categoryValidation) throws NotFoundException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada com o id :: " + id));
        category.setName(categoryValidation.getName());
        return categoryRepository.save(category);
    }

    public void delete(Long id) throws NotFoundException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada com o id :: " + id));
        categoryRepository.delete(category);
    }

    public Category findById(Long id) throws NotFoundException {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada com o id :: " + id));
    }

    public Page<Category> findAll(int offset, int limit) {
        return categoryRepository.findAll(PageRequest.of(offset, limit));
    }

}
