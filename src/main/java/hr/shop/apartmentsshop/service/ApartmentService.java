package hr.shop.apartmentsshop.service;

import hr.shop.apartmentsshop.dto.ApartmentResDTO;

import java.util.List;
import java.util.Optional;

public interface ApartmentService {
    List<ApartmentResDTO> getApartments(String searchTerm);
}
