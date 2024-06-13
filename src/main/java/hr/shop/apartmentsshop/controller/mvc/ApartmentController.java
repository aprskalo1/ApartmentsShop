package hr.shop.apartmentsshop.controller.mvc;

import hr.shop.apartmentsshop.dto.ApartmentResDTO;
import hr.shop.apartmentsshop.service.ApartmentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/apartments")
@AllArgsConstructor
public class ApartmentController {

    private ApartmentService apartmentService;

    @GetMapping("/getApartments")
    public String getApartments(@RequestParam(value = "searchTerm", required = false) String searchTerm, Model model) {
        List<ApartmentResDTO> apartments = apartmentService.getApartments(searchTerm);
        model.addAttribute("apartments", apartments);
        return "apartments";
    }
}
