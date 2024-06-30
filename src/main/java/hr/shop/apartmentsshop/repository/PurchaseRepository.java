package hr.shop.apartmentsshop.repository;

import hr.shop.apartmentsshop.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer>, JpaSpecificationExecutor<Purchase> {
    List<Purchase> findAllByUserId(Integer userId);
}
