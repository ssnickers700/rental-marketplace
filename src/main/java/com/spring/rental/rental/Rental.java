package com.spring.rental.rental;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.rental.address.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.rental.client.Client;
import com.spring.rental.item.Item;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ToString
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "rental")
public class Rental {
    @Id
    @SequenceGenerator(name = "rental_sequence", sequenceName = "rental_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rental_sequence")
    private Long id;

    @NonNull
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JoinColumn(
            name = "renter_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "client_rental_renter_id_fk")
    )
    private Client renter;

    @NonNull
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JoinColumn(
            name = "rentee_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "client_rental_rentee_id_fk")
    )
    private Client rentee;

    @NonNull
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JoinColumn(
            name = "item_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "item_rental_id_fk")
    )
    private Item item;

    @NonNull
    @Column(name = "item_price_id", nullable = false)
    private Long itemPriceId;

    @NonNull
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JoinColumn(
            name = "renter_address_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "rental_renter_address_id_fk")
    )
    private Address renterAddress;

    @NonNull
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JoinColumn(
            name = "rentee_address_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "rental_rentee_address_id_fk")
    )
    private Address renteeAddress;

    @NonNull
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status must not be empty")
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "delivery_ordered_at")
    private LocalDateTime deliveryOrderedAt;

    @Column(name = "in_delivery_at")
    private LocalDateTime inDeliveryAt;

    @Column(name = "delivered_at")
    private LocalDateTime deliveredAt;

    @Column(name = "return_ordered_at")
    private LocalDateTime returnOrderedAt;

    @Column(name = "in_return_at")
    private LocalDateTime inReturnAt;

    @Column(name = "returned_at")
    private LocalDateTime returnedAt;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    @Column(name = "canceled_at")
    private LocalDateTime canceledAt;

    @NonNull
    @NotNull(message = "Declared end date must not be empty")
    @Column(name = "declared_end_date", nullable = false)
    private LocalDateTime declaredEndDate;

    @Column(name = "bought_out_at")
    private LocalDateTime boughtOutAt;

    @Column(name = "buyout_price")
    private BigDecimal buyoutPrice;

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

