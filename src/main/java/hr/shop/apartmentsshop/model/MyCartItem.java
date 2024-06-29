package hr.shop.apartmentsshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MyCart")
public class MyCartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "apartment_id", referencedColumnName = "id", nullable = false)
    private Apartment apartment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "totalPrice", nullable = false)
    private Double totalPrice;

    @Column(name = "isBought", nullable = false)
    private Boolean isBought = false;
}
