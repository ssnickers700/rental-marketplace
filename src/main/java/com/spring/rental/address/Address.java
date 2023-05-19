package com.spring.rental.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.rental.client.Client;
import com.spring.rental.rental.Rental;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@ToString
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @SequenceGenerator(name = "address_sequence", sequenceName = "address_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
    private Long id;

    @NonNull
    @ManyToOne
    @JoinColumn(
            name = "client_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "address_client_id_fk")
    )
    private Client client;

    @NonNull
    @NotBlank(message = "Street must not be empty")
    @Column(name = "street", nullable = false)
    private String street;

    @NonNull
    @NotBlank(message = "City must not be empty")
    @Column(name = "city", nullable = false)
    private String city;

    @NonNull
    @NotBlank(message = "Postal code must not be empty")
    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @NonNull
    @NotBlank(message = "Country must not be empty")
    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "note")
    private String note;

    @JsonIgnore
    @OneToMany(mappedBy = "renterAddress", cascade = CascadeType.PERSIST)
    private List<Rental> rentalsAsRenterAddress;

    @JsonIgnore
    @OneToMany(mappedBy = "renteeAddress", cascade = CascadeType.PERSIST)
    private List<Rental> rentalsAsRenteeAddress;

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

