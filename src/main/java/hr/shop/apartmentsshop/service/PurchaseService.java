package hr.shop.apartmentsshop.service;

import hr.shop.apartmentsshop.model.MyCartItem;
import hr.shop.apartmentsshop.model.Purchase;
import hr.shop.apartmentsshop.model.PurchaseType;
import hr.shop.apartmentsshop.model.User;

import java.util.List;

public interface PurchaseService {
    void addPurchase(User user, Integer myCartItemId, PurchaseType purchaseType);
    List<Purchase> findAllByUserId(Integer userId);
    List<Purchase> findAllByUserId();
}
