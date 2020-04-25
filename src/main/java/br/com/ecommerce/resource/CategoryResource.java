package br.com.ecommerce.resource;

import br.com.ecommerce.exception.NotFoundException;
import br.com.ecommerce.model.Category;
import br.com.ecommerce.service.CategoryService;
import br.com.ecommerce.validation.CategoryValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/")
public class CategoryResource {

    private final CategoryService categoryService;

    @Autowired
    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public ResponseEntity<Category> save(@RequestBody CategoryValidation categoryValidation) {
        return new ResponseEntity<>(categoryService.save(categoryValidation), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Category> update(@PathVariable(value = "id") Long id,
                                           CategoryValidation categoryValidation) throws NotFoundException {
        return new ResponseEntity<>(categoryService.update(id, categoryValidation), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Category> update(@PathVariable(value = "id") Long id) throws NotFoundException {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public ResponseEntity<Category> findById(@PathVariable(value = "id") Long id) throws NotFoundException {
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public Page<Category> findByAll(
            @RequestParam(value = "limit", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "10") int limit
    ) {
        return categoryService.findAll(offset, limit);
    }

}
