package hr.shop.apartmentsshop.specification;

import hr.shop.apartmentsshop.model.Apartment;
import org.springframework.data.jpa.domain.Specification;

public class ApartmentSpecification {

    public static Specification<Apartment> containsSearchTerm(String searchTerm) {
        return (root, query, criteriaBuilder) -> {
            if (searchTerm == null || searchTerm.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            String likePattern = "%" + searchTerm.toLowerCase() + "%";
            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("location")), likePattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("category").get("name")), likePattern)
            );
        };
    }

    public static Specification<Apartment> hasQuantityGreaterThanZero() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("quantity"), 0);
    }
}
