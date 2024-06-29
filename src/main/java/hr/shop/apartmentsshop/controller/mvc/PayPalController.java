package hr.shop.apartmentsshop.controller.mvc;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import hr.shop.apartmentsshop.model.MyCartItem;
import hr.shop.apartmentsshop.model.PurchaseType;
import hr.shop.apartmentsshop.model.User;
import hr.shop.apartmentsshop.service.PurchaseService;
import hr.shop.apartmentsshop.service.UserService;
import hr.shop.apartmentsshop.service.paypal.PayPalService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/paypal")
public class PayPalController {
    private static final Logger log = LoggerFactory.getLogger(PayPalController.class);
    private final PayPalService payPalService;
    private final PurchaseService purchaseService;
    private final UserService userService;

    @PostMapping("/payment/create")
    public RedirectView createPayment(@RequestParam("totalPrice") double totalPrice, @RequestParam("myCartItemId") Integer id) {
        try {
            String cancelUrl = "http://localhost:8080/paypal/payment/cancel";
            String successUrl = "http://localhost:8080/paypal/payment/success?myCartItemId=" + id;

            Payment payment = payPalService.createPayment(
                    totalPrice,
                    "USD",
                    "paypal",
                    "sale",
                    "Payment with PayPal purchase of apartments",
                    cancelUrl,
                    successUrl
            );

            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return new RedirectView(link.getHref());
                }
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }

        return new RedirectView("/error");
    }


    @GetMapping("/payment/success")
    public String paymentSuccess(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, @RequestParam("myCartItemId") Integer myCartItemId) {
        try {
            Payment payment = payPalService.executePayment(paymentId, payerId);

            if (payment.getState().equals("approved")) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                User user = userService.findByUsername(authentication.getName());

                purchaseService.addPurchase(user, myCartItemId, PurchaseType.PAYPAL);

                return "paypal/paymentSuccess";
            }

        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }

        return "paypal/paymentSuccess";
    }

    @GetMapping("/payment/cancel")
    public String paymentCancel() {
        return "paypal/paymentCancel";
    }

    @GetMapping("/payment/error")
    public String paymentError() {
        return "paypal/paymentError";
    }
}
