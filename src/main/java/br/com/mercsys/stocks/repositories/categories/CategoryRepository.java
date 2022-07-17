package br.com.mercsys.stocks.repositories.categories;

import java.util.List;

import br.com.mercsys.stocks.entities.categories.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    Category findByName(String name);

    List<Category> findByNameContainsIgnoreCase(String name);
}
