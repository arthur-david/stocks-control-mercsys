package br.com.mercsys.stocks.services.categories;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.util.Comparator;
import java.util.List;

import br.com.mercsys.stocks.entities.categories.Category;
import br.com.mercsys.stocks.repositories.categories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    
    public Category create(String name) {
        Category category = categoryRepository.findByName(name);
        
        if(nonNull(category)) return category;

        category = new Category(name);
        return save(category);
    }

    public List<Category> findAll() {
        List<Category> categories = categoryRepository.findAll();
        categories.sort(Comparator.comparing(Category::getName));
        return categories;
    }

    public List<Category> search(String name) {
        if(name.isEmpty() || name.isBlank()) return List.of();

        return categoryRepository.findByNameContainsIgnoreCase(name);
    }

    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }
    
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category update(String oldName, String newName) {
        Category category = findByName(oldName);

        if(isNull(category)) return null;

        category.setName(newName);
        return save(category);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    public Category save(Category category) {
        return categoryRepository.saveAndFlush(category);
    }
}
