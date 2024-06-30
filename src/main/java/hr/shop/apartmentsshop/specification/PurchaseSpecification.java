package hr.shop.apartmentsshop.specification;

import hr.shop.apartmentsshop.model.Purchase;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class PurchaseSpecification {

    public static Specification<Purchase> customerNameContains(String customerName) {
        return (root, query, criteriaBuilder) -> {
            if (customerName == null || customerName.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            String likePattern = "%" + customerName.toLowerCase() + "%";
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("user").get("username")), likePattern);
        };
    }

    public static Specification<Purchase> purchaseDateBetween(Date startDate, Date endDate) {
        return (root, query, criteriaBuilder) -> {
            if (startDate == null && endDate == null) {
                return criteriaBuilder.conjunction();
            } else if (startDate == null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("purchaseDate"), endDate);
            } else if (endDate == null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("purchaseDate"), startDate);
            } else {
                return criteriaBuilder.between(root.get("purchaseDate"), startDate, endDate);
            }
        };
    }
}
