package hr.shop.apartmentsshop.service;

import hr.shop.apartmentsshop.dto.ApartmentReqDTO;
import hr.shop.apartmentsshop.dto.ApartmentResDTO;

import java.util.List;

public interface ApartmentService {
    List<ApartmentResDTO> getApartments(String searchTerm);
    void createApartment(ApartmentReqDTO apartmentReqDTO);
}
