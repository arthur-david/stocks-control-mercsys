package br.com.mercsys.stocks.controllers.categories;

import java.net.URI;
import java.util.List;

import br.com.mercsys.stocks.entities.categories.Category;
import br.com.mercsys.stocks.services.categories.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping(value = "create/{name}")
    public ResponseEntity<Void> create(@PathVariable String name) {
        Category category = categoryService.create(name);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("categories/{name}").buildAndExpand(category.getName()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "all")
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }
    
    @GetMapping(value = "search/{name}")
    public ResponseEntity<List<Category>> search(@PathVariable String name) {
        List<Category> categories = categoryService.search(name);
        return ResponseEntity.ok(categories);
    }

    @GetMapping(value = "{name}")
    public ResponseEntity<Category> getByName(@PathVariable String name) {
        Category category = categoryService.findByName(name);
        return ResponseEntity.ok(category);
    }
    
    @GetMapping(value = "get/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }

    @PatchMapping("update")
    public ResponseEntity<Void> update(@RequestHeader String oldName, @RequestHeader String newName) {
        categoryService.update(oldName, newName);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
