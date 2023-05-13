package com.spring.rental.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@ToString
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "client")
public class Client {
    @Id
    @SequenceGenerator(name = "client_sequence", sequenceName = "client_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence")
    private Long id;

    @NonNull
    @NotBlank(message = "Email must not be empty")
    @Column(unique = true, nullable = false)
    private String email;

    @NonNull
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
    @NotBlank(message = "Role must not be empty")
    @Column(nullable = false)
    private Role role;

    @Column(name = "karma_score", nullable = false)
    private Integer karmaScore = 0;

    @JsonIgnore
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
