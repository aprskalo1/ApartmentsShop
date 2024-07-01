package hr.shop.apartmentsshop.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class LoginSuccessEvent extends ApplicationEvent {

    private final String username;
    private final String ipAddress;

    public LoginSuccessEvent(Object source, String username, String ipAddress) {
        super(source);
        this.username = username;
        this.ipAddress = ipAddress;
    }
}
