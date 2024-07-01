package hr.shop.apartmentsshop.controller.mvc;

import hr.shop.apartmentsshop.model.LoginLog;
import hr.shop.apartmentsshop.service.LoginLogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/log")
@AllArgsConstructor
public class LoginLogController {
    private final LoginLogService loginLogService;

    @GetMapping("/loginlog")
    public String getLoginLog(Model model) {
        List<LoginLog> loginLogs = loginLogService.getLoginLogs();
        model.addAttribute("loginLogs", loginLogs);
        return "loginLog";
    }
}
