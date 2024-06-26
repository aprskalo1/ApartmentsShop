package hr.shop.apartmentsshop.controller.mvc;


import hr.shop.apartmentsshop.model.MyCartItem;
import hr.shop.apartmentsshop.model.User;
import hr.shop.apartmentsshop.service.MyCartService;
import hr.shop.apartmentsshop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/myCart")
@AllArgsConstructor
public class MyCartController {
    private final MyCartService myCartService;
    private final UserService userService;

    @PostMapping("/add")
    public String addApartmentToCart(Integer apartmentId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Long userId = null;
        User user = userService.findByUsername(username);

        if (user != null) {
            userId = Long.valueOf(user.getId());
        }

        myCartService.addApartmentToCart(apartmentId, userId);
        return "redirect:/apartments/get";
    }

    @GetMapping("/view")
    public String getCartItems(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<MyCartItem> cartItems;

        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            cartItems = myCartService.getCartItemsWithoutUser();
        } else {
            String username = authentication.getName();
            User user = userService.findByUsername(username);
            cartItems = myCartService.getCartItemsByUser(Long.valueOf(user.getId()));
        }

        model.addAttribute("cartItems", cartItems);
        return "cartItemsView";
    }

    @PostMapping("/remove")
    public String removeApartmentFromCart(Integer apartmentId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Long userId = null;
        User user = userService.findByUsername(username);

        if (user != null) {
            userId = Long.valueOf(user.getId());
        }

        myCartService.removeApartmentFromCart(apartmentId, userId);
        return "redirect:/myCart/view";
    }
}