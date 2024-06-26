package hr.shop.apartmentsshop.service;

import hr.shop.apartmentsshop.model.Apartment;
import hr.shop.apartmentsshop.model.MyCartItem;
import hr.shop.apartmentsshop.model.User;
import hr.shop.apartmentsshop.repository.ApartmentRepository;
import hr.shop.apartmentsshop.repository.MyCartRepository;
import hr.shop.apartmentsshop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MyCartServiceImpl implements MyCartService {
    private ApartmentRepository apartmentRepository;
    private UserRepository userRepository;
    private MyCartRepository myCartRepository;

    @Override
    public void addApartmentToCart(Integer apartmentId, Long userId) {
        MyCartItem myCartItem;
        Optional<MyCartItem> existingCartItem;

        Apartment apartment = apartmentRepository.findById(apartmentId)
                .orElseThrow(() -> new RuntimeException("Apartment not found"));

        double apartmentPrice = apartment.getSize() * apartment.getPrice();

        if (userId != null) {
            existingCartItem = myCartRepository.findByApartmentIdAndUserId(apartmentId, userId);
        } else {
            existingCartItem = myCartRepository.findByApartmentIdAndUserIsNull(apartmentId);
        }

        if (existingCartItem.isPresent()) {
            myCartItem = existingCartItem.get();
            myCartItem.setQuantity(myCartItem.getQuantity() + 1);
            myCartItem.setTotalPrice(myCartItem.getTotalPrice() + apartmentPrice);
        } else {
            myCartItem = new MyCartItem();
            myCartItem.setApartment(apartment);
            myCartItem.setQuantity(1);
            myCartItem.setTotalPrice(apartmentPrice);

            if (userId != null) {
                User user = userRepository.findById(userId)
                        .orElseThrow(() -> new RuntimeException("User not found"));
                myCartItem.setUser(user);
            }
        }

        apartment.setQuantity(apartment.getQuantity() - 1);

        if (apartment.getQuantity() < 0) {
            throw new RuntimeException("Apartment is out of stock");
        }

        apartmentRepository.save(apartment);
        myCartRepository.save(myCartItem);
    }

    @Override
    public void removeApartmentFromCart(Integer apartmentId, Long userId) {
        MyCartItem myCartItem;
        Optional<MyCartItem> existingCartItem;

        if (userId != null) {
            existingCartItem = myCartRepository.findByApartmentIdAndUserId(apartmentId, userId);
        } else {
            existingCartItem = myCartRepository.findByApartmentIdAndUserIsNull(apartmentId);
        }

        if (existingCartItem.isEmpty()) {
            throw new RuntimeException("Cart item not found");
        }

        myCartItem = existingCartItem.get();

        if (myCartItem.getQuantity() == 1) {
            myCartRepository.delete(myCartItem);
        } else {
            myCartItem.setQuantity(myCartItem.getQuantity() - 1);
            myCartItem.setTotalPrice(myCartItem.getTotalPrice() - myCartItem.getApartment().getSize() * myCartItem.getApartment().getPrice());
            myCartRepository.save(myCartItem);
        }

        Apartment apartment = myCartItem.getApartment();
        apartment.setQuantity(apartment.getQuantity() + 1);
        apartmentRepository.save(apartment);
    }

    @Override
    public List<MyCartItem> getCartItemsByUser(Long userId) {
        return myCartRepository.findByUserId(userId);
    }

    @Override
    public List<MyCartItem> getCartItemsWithoutUser() {
        return myCartRepository.findByUserIsNull();
    }

    @Override
    public void deleteAllWithApartmentId(Integer apartmentId) {
        List<MyCartItem> myCartItems = myCartRepository.findByApartmentId(apartmentId);
        myCartRepository.deleteAll(myCartItems);
    }
}
