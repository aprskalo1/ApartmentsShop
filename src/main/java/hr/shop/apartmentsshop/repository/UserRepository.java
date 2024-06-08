package hr.shop.apartmentsshop.repository;

import hr.shop.apartmentsshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
