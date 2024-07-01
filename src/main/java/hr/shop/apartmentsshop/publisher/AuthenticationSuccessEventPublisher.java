package hr.shop.apartmentsshop.publisher;

import hr.shop.apartmentsshop.event.LoginSuccessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessEventPublisher implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        Authentication authentication = event.getAuthentication();
        String username = authentication.getName();
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        String ipAddress = details.getRemoteAddress();

        LoginSuccessEvent loginSuccessEvent = new LoginSuccessEvent(this, username, ipAddress);
        applicationEventPublisher.publishEvent(loginSuccessEvent);
    }
}
