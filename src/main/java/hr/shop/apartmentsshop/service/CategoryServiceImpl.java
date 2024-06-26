package hr.shop.apartmentsshop.service;

import hr.shop.apartmentsshop.model.Category;
import hr.shop.apartmentsshop.repository.ApartmentRepository;
import hr.shop.apartmentsshop.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ApartmentRepository apartmentRepository;

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Integer id) {
        if (apartmentRepository.existsByCategoryId(id)) throw new RuntimeException("Category is in use");
        categoryRepository.deleteById(id);
    }

    @Override
    public void updateCategory(Category category) {
        categoryRepository.save(category);
    }
}
