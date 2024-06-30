package hr.shop.apartmentsshop.service;

import hr.shop.apartmentsshop.model.MyCartItem;
import hr.shop.apartmentsshop.model.Purchase;
import hr.shop.apartmentsshop.model.PurchaseType;
import hr.shop.apartmentsshop.model.User;
import hr.shop.apartmentsshop.repository.MyCartRepository;
import hr.shop.apartmentsshop.repository.PurchaseRepository;
import hr.shop.apartmentsshop.specification.ApartmentSpecification;
import hr.shop.apartmentsshop.specification.PurchaseSpecification;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final MyCartRepository myCartRepository;

    @Override
    public void addPurchase(User user, Integer myCartItemId, PurchaseType purchaseType) {
        MyCartItem myCartItem = myCartRepository.findById(myCartItemId).orElseThrow(()
                -> new RuntimeException("MyCartItem not found"));

        Purchase purchase = new Purchase(user, myCartItem.getApartment().getLocation(), myCartItem.getQuantity(), new Date(), purchaseType);
        purchaseRepository.save(purchase);
        myCartRepository.deleteById(myCartItemId);
    }

    @Override
    public List<Purchase> findAllByUserId(Integer userId) {
        return purchaseRepository.findAllByUserId(userId);
    }

    @Override
    public List<Purchase> findAllFiltered(Date startDate, Date endDate, String customerName) {
        Specification<Purchase> spec = PurchaseSpecification.customerNameContains(customerName)
                .and(PurchaseSpecification.purchaseDateBetween(startDate, endDate));
        return purchaseRepository.findAll(spec);
    }
}
