package hr.shop.apartmentsshop.listener;

import hr.shop.apartmentsshop.event.LoginSuccessEvent;
import hr.shop.apartmentsshop.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccessEventListener implements ApplicationListener<LoginSuccessEvent> {

    @Autowired
    private LoginLogService loginLogService;

    @Override
    public void onApplicationEvent(LoginSuccessEvent event) {
        loginLogService.saveLoginLog(event.getUsername(), event.getIpAddress());
    }
}
