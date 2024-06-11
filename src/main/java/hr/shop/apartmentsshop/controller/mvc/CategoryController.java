package hr.shop.apartmentsshop.controller.mvc;

import hr.shop.apartmentsshop.model.Category;
import hr.shop.apartmentsshop.repository.CategoryRepository;
import hr.shop.apartmentsshop.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String getCategories(Model model) {
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("category", new Category());
        return "categories";
    }

    @PostMapping("/saveCategory")
    public String saveCategory(Model model, Category category, RedirectAttributes redirectAttrs) {
        try {
            categoryService.saveCategory(category);
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/categories";
    }

    @PostMapping("/updateCategory")
    public String updateCategory(Model model, Category category, RedirectAttributes redirectAttrs) {
        try {
            Category existingCategory = categoryService.getCategories().stream().filter(c -> c.getId().equals(category.getId())).findFirst().orElse(null);
            if (existingCategory == null) {
                throw new Exception("Category not found");
            }
            existingCategory.setName(category.getName());
            categoryService.updateCategory(existingCategory);
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/categories";
    }

    @PostMapping("/deleteCategory")
    public String deleteCategory(Model model, Category category) {
        categoryService.deleteCategory(category.getId());
        return "redirect:/categories";
    }
}
