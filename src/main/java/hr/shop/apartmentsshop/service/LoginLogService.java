package hr.shop.apartmentsshop.service;

import hr.shop.apartmentsshop.model.LoginLog;

import java.util.List;

public interface LoginLogService {
    void saveLoginLog(String username, String ipAddress);
    List<LoginLog> getLoginLogs();
}
