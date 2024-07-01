package hr.shop.apartmentsshop.service;

import hr.shop.apartmentsshop.model.LoginLog;
import hr.shop.apartmentsshop.repository.LoginLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class LoginLogServiceImpl implements LoginLogService {
    private LoginLogRepository loginLogRepository;

    @Override
    public void saveLoginLog(String username, String ipAddress) {
        loginLogRepository.save(new LoginLog(username, ipAddress, LocalDateTime.now()));
    }

    @Override
    public List<LoginLog> getLoginLogs() {
        return loginLogRepository.findAll();
    }
}
