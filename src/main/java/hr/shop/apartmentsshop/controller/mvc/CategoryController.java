package hr.shop.apartmentsshop.controller.mvc;

import hr.shop.apartmentsshop.model.Category;
import hr.shop.apartmentsshop.repository.CategoryRepository;
import hr.shop.apartmentsshop.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
