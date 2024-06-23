package hr.shop.apartmentsshop.controller.mvc;

import hr.shop.apartmentsshop.dto.ApartmentReqDTO;
import hr.shop.apartmentsshop.dto.ApartmentResDTO;
import hr.shop.apartmentsshop.model.Category;
import hr.shop.apartmentsshop.repository.CategoryRepository;
import hr.shop.apartmentsshop.service.ApartmentService;
import lombok.AllArgsConstructor;
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

    @GetMapping("/get")
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
        return "apartmentCreate";
    }

    @PostMapping("/create")
    public String createApartment(ApartmentReqDTO apartmentReqDTO) {
        apartmentService.createApartment(apartmentReqDTO);
        return "redirect:/apartments/get";
    }

    @PostMapping("/delete")
    public String deleteApartment(@RequestParam("apartmentId") Integer apartmentId) {
        apartmentService.deleteApartment(apartmentId);
        return "redirect:/apartments/get";
    }

    @GetMapping("/update")
    public String showUpdateForm(@RequestParam("apartmentId") Integer apartmentId, Model model) {
        ApartmentResDTO apartment = apartmentService.getApartmentById(apartmentId);
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("apartment", apartment);
        model.addAttribute("categories", categories);
        return "apartmentUpdate";
    }

    @PostMapping("/update")
    public String updateApartment(ApartmentReqDTO apartmentReqDTO) {
        apartmentService.updateApartment(apartmentReqDTO);
        return "redirect:/apartments/get";
    }
}
