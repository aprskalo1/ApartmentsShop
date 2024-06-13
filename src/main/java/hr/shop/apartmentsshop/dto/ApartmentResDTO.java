package hr.shop.apartmentsshop.dto;

import hr.shop.apartmentsshop.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentResDTO {
    private Integer id;
    private String location;
    private Double price;
    private Double size;
    private Integer rooms;
    private Integer quantity;
    private String pictureUrl;
    private Category category;
}
