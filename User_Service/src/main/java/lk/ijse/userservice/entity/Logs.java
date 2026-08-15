package lk.ijse.userservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Logs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(length = 255)
    private String action;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private LocalDateTime timestamp;

    @Column(columnDefinition = "TEXT")
    private String details;
}