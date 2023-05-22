package com.spring.rental.itemprice;

import com.fasterxml.jackson.annotation.*;
import com.spring.rental.View;
import com.spring.rental.item.Item;
import com.spring.rental.rental.Rental;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@ToString
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "item_price")
public class ItemPrice {
    @Id
    @SequenceGenerator(name = "item_price_sequence", sequenceName = "item_price_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_price_sequence")
    private Long id;

    @NonNull
    @JsonView(View.Extended.class)
    @ManyToOne
    @JoinColumn(
            name = "item_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "item_item_price_id_fk")
    )
    private Item item;

    @NonNull
    @JsonView(View.Basic.class)
    @NotNull(message = "Month must not be empty")
    @Column(name = "month", nullable = false)
    private Integer month;

    @NonNull
    @JsonView(View.Basic.class)
    @NotNull(message = "Price must not be empty")
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @JsonIgnore
    @OneToMany(mappedBy = "itemPrice")
    private List<Rental> rentals;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @CreationTimestamp
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @JsonIgnore
    @UpdateTimestamp
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}

