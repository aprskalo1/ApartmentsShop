package hr.shop.apartmentsshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Apartment")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "location", length = 50, nullable = false)
    private String location;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "size", nullable = false)
    private Double size;

    @Column(name = "rooms", nullable = false)
    private Integer rooms;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "pictureUrl", nullable = false)
    private String pictureUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;
}
