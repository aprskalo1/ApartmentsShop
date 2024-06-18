package hr.shop.apartmentsshop.controller.mvc;

import hr.shop.apartmentsshop.dto.ApartmentReqDTO;
import hr.shop.apartmentsshop.dto.ApartmentResDTO;
import hr.shop.apartmentsshop.model.Category;
import hr.shop.apartmentsshop.repository.CategoryRepository;
import hr.shop.apartmentsshop.service.ApartmentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/apartments")
@AllArgsConstructor
public class ApartmentController {

    private final CategoryRepository categoryRepository;
    private ApartmentService apartmentService;

    @GetMapping("/getApartments")
    public String getApartments(@RequestParam(value = "searchTerm", required = false) String searchTerm, Model model) {
        List<ApartmentResDTO> apartments = apartmentService.getApartments(searchTerm);
        model.addAttribute("apartments", apartments);
        return "apartments";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("apartment", new ApartmentReqDTO());
        model.addAttribute("categories", categories);
        return "createApartment";
    }

    @PostMapping("/create")
    public String createApartment(ApartmentReqDTO apartmentReqDTO) {
        apartmentService.createApartment(apartmentReqDTO);
        return "redirect:/apartments/getApartments";
    }
}
