package hr.shop.apartmentsshop.service;

import hr.shop.apartmentsshop.model.MyCartItem;

import java.util.List;

public interface MyCartService {
    void addApartmentToCart(Integer apartmentId, Long userId);
    void removeApartmentFromCart(Integer apartmentId, Long userId);
    List<MyCartItem> getCartItemsByUser(Long userId);
    List<MyCartItem> getCartItemsWithoutUser();
    void deleteAllWithApartmentId(Integer apartmentId);
}
