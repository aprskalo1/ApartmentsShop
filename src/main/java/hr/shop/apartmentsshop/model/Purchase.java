package hr.shop.apartmentsshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "apartment_location", nullable = false)
    private String apartmentLocation;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @CreatedDate
    @Column(name = "purchase_date", nullable = false)
    private Date purchaseDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "purchase_type", nullable = false)
    private PurchaseType purchaseType;

    public Purchase(User user, String apartmentLocation, Integer quantity, Date purchaseDate, PurchaseType purchaseType) {
        this.user = user;
        this.apartmentLocation = apartmentLocation;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
        this.purchaseType = purchaseType;
    }
}
