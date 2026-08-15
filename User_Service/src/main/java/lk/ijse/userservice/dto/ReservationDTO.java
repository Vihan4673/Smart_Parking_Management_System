package lk.ijse.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationDTO {
    private Long id;
    private Long userId;
    private Long vehicleId;
    private Long spaceId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Status status;
    private LocalDateTime createdAt;

    public enum Status {
        PENDING,
        ACTIVE,
        COMPLETED,
        CANCELLED
    }
}
