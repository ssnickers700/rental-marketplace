package com.spring.rental.client;

import com.fasterxml.jackson.annotation.*;
import com.spring.rental.address.Address;
import com.spring.rental.item.Item;
import com.spring.rental.rental.Rental;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "client", uniqueConstraints = {@UniqueConstraint(name = "client_email_unique", columnNames = "email")})
public class Client {
    @Id
    @SequenceGenerator(name = "client_sequence", sequenceName = "client_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence")
    private Long id;

    @NonNull
    @NotBlank(message = "Email must not be empty")
    @Column(nullable = false)
    private String email;

    @NonNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Password must not be empty")
    @Column(nullable = false)
    private String password;

    @NonNull
    @NotBlank(message = "First name must not be empty")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NonNull
    @NotBlank(message = "Last name must not be empty")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NonNull
    @Column(name = "phone_number", nullable = false)
    private Integer phoneNumber;

    @NonNull
    @Enumerated(value = EnumType.STRING)
    @NotNull(message = "Role must not be empty")
    @Column(nullable = false)
    private Role role;

    @Column(name = "karma_score", nullable = false)
    private Integer karmaScore = 0;

    @JsonIgnore
    @OneToMany(mappedBy = "renter", cascade = CascadeType.PERSIST)
    private List<Rental> rentalsAsRenter;

    @JsonIgnore
    @OneToMany(mappedBy = "rentee", cascade = CascadeType.PERSIST)
    private List<Rental> rentalsAsRentee;

    @JsonIgnore
    @OneToMany(mappedBy = "client", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Address> addresses;

    @JsonIgnore
    @OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST)
    private List<Item> items;

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
