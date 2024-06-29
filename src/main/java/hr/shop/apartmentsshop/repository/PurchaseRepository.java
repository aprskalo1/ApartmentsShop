package hr.shop.apartmentsshop.repository;

import hr.shop.apartmentsshop.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    List<Purchase> findAllByUserId(Integer userId);
}
