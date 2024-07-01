package hr.shop.apartmentsshop.repository;

import hr.shop.apartmentsshop.model.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginLogRepository extends JpaRepository<LoginLog, Long> {
}
