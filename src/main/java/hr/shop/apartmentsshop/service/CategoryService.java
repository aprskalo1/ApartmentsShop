package hr.shop.apartmentsshop.service;

import hr.shop.apartmentsshop.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();

    void saveCategory(Category category);

    void deleteCategory(Integer id);

    void updateCategory(Category category);
}
