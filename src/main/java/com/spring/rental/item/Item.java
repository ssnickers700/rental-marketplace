package com.spring.rental.item;

import com.fasterxml.jackson.annotation.*;
import com.spring.rental.category.Category;
import com.spring.rental.client.Client;
import com.spring.rental.rental.Rental;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ToString
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "item")
public class Item {
    @Id
    @SequenceGenerator(name = "item_sequence", sequenceName = "item_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_sequence")
    private Long id;

    @NonNull
    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "client_item_user_id_fk")
    )
    private Client client;

    @NonNull
    @ManyToOne
    @JoinColumn(
            name = "category_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "category_item_user_id_fk")
    )
    private Category category;

    @NonNull
    @NotBlank(message = "Title must not be empty")
    @Column(name = "title", nullable = false)
    private String title;

    @NonNull
    @NotBlank(message = "Description must not be empty")
    @Column(name = "description", nullable = false)
    private String description;

    @NonNull
    @Enumerated(EnumType.STRING)
    @NotNull(message = "State must not be empty")
    @Column(name = "state", nullable = false)
    private State state;

    @NonNull
    @NotNull(message = "Base price must not be empty")
    @Column(name = "base_price", nullable = false)
    private BigDecimal basePrice;

    @Column(name = "availability_deadline")
    private LocalDate availabilityDeadline;

    @JsonIgnore
    @OneToMany(mappedBy = "item", cascade = CascadeType.PERSIST)
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

