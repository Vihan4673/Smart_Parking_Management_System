package lk.ijse.vehicleservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "vehicles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Assuming User entity is in another service, you can map user_id as Long or as a relation if User entity is available
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "license_plate", nullable = false, unique = true, length = 20)
    private String licensePlate;

    @Column(name = "vehicle_type", length = 50)
    private String vehicleType;

    @Column(length = 50)
    private String brand;

    @Column(length = 50)
    private String model;

    @Column(name = "registered_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private LocalDateTime registeredAt;
}
