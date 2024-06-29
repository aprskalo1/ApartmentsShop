package hr.shop.apartmentsshop.controller.mvc;

import hr.shop.apartmentsshop.model.User;
import hr.shop.apartmentsshop.service.PurchaseService;
import hr.shop.apartmentsshop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/purchase")
@AllArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;
    private final UserService userService;

    @GetMapping("/myPurchases")
    public String myPurchases(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userService.findByUsername(username);

        model.addAttribute("purchases", purchaseService.findAllByUserId(user.getId()));
        return "purchases";
    }

    @GetMapping("/allPurchases")
    public String allPurchases(Model model) {
        model.addAttribute("purchases", purchaseService.findAllByUserId());
        return "purchases";
    }
}
