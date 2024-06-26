package hr.shop.apartmentsshop.repository;

import hr.shop.apartmentsshop.model.MyCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MyCartRepository extends JpaRepository<MyCartItem, Integer> {
    List<MyCartItem> findByUserId(Long user_id);
    List<MyCartItem> findByUserIsNull();
    Optional<MyCartItem> findByApartmentIdAndUserId(Integer apartmentId, Long userId);
    Optional<MyCartItem> findByApartmentIdAndUserIsNull(Integer apartmentId);
    List<MyCartItem> findByApartmentId(Integer apartmentId);
}