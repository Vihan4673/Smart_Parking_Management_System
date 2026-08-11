package lk.ijse.parking_space_service.entity;

import jakarta.persistence.*;
import lk.ijse.parking_space_service.util.ParkingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "parking_spaces")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParkingSpace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @Column(name = "zone_id", nullable = false)
    private Long zoneId;

    @Column(name = "location_description", columnDefinition = "TEXT")
    private String locationDescription;

    @Column(name = "is_available", nullable = false)
    private boolean isAvailable = true;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('AVAILABLE', 'RESERVED', 'OCCUPIED') DEFAULT 'AVAILABLE'")
    private ParkingStatus status = ParkingStatus.AVAILABLE;

    @Column(name = "hourly_rate", precision = 10, scale = 2)
    private BigDecimal hourlyRate;

    @Column(name = "iot_device_id", length = 100)
    private String iotDeviceId;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;
}