package hr.shop.apartmentsshop.repository;

import hr.shop.apartmentsshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
