package hr.shop.apartmentsshop.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String redirectToHomePage() {
        return "redirect:/apartments/getApartments";
    }
}
