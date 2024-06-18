package hr.shop.apartmentsshop.service;

import hr.shop.apartmentsshop.dto.ApartmentReqDTO;
import hr.shop.apartmentsshop.dto.ApartmentResDTO;
import hr.shop.apartmentsshop.model.Apartment;
import hr.shop.apartmentsshop.model.Category;
import hr.shop.apartmentsshop.repository.ApartmentRepository;
import hr.shop.apartmentsshop.repository.CategoryRepository;
import hr.shop.apartmentsshop.specification.ApartmentSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {
    private final CategoryRepository categoryRepository;
    private ApartmentRepository apartmentRepository;

    @Override
    public List<ApartmentResDTO> getApartments(String searchTerm) {
        Specification<Apartment> spec = ApartmentSpecification.containsSearchTerm(searchTerm);
        return apartmentRepository.findAll(spec).stream()
                .map(this::mapApartmentToApartmentResDTO)
                .toList();
    }

    @Override
    public void createApartment(ApartmentReqDTO apartmentReqDTO) {
        Category category = categoryRepository.findById(apartmentReqDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));

        Apartment apartment = mapApartmentReqDTOToApartment(apartmentReqDTO, category);
        apartmentRepository.save(apartment);
    }

    private ApartmentResDTO mapApartmentToApartmentResDTO(Apartment apartment) {
        return new ApartmentResDTO(
                apartment.getId(),
                apartment.getLocation(),
                apartment.getPrice(),
                apartment.getSize(),
                apartment.getRooms(),
                apartment.getQuantity(),
                apartment.getPictureUrl(),
                apartment.getCategory()
        );
    }

    private Apartment mapApartmentReqDTOToApartment(ApartmentReqDTO apartmentReqDTO, Category category) {
        return new Apartment(
                null,
                apartmentReqDTO.getLocation(),
                apartmentReqDTO.getPrice(),
                apartmentReqDTO.getSize(),
                apartmentReqDTO.getRooms(),
                apartmentReqDTO.getQuantity(),
                apartmentReqDTO.getPictureUrl(),
                category
        );
    }
}
