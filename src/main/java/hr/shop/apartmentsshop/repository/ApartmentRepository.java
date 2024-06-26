package hr.shop.apartmentsshop.repository;

import hr.shop.apartmentsshop.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ApartmentRepository extends JpaRepository<Apartment, Integer>, JpaSpecificationExecutor<Apartment> {
    boolean existsByCategoryId(Integer id);
}
