package hr.shop.apartmentsshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentReqDTO {
    private String location;
    private Double price;
    private Double size;
    private Integer rooms;
    private Integer quantity;
    private String pictureUrl;
    private Integer categoryId;
}