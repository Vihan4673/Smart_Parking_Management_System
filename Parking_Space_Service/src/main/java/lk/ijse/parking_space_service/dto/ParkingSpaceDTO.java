package lk.ijse.parking_space_service.dto;

import lk.ijse.parking_space_service.util.ParkingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParkingSpaceDTO {
    private Long id;
    private String location;
    private String slotCode;
    private boolean isAvailable;
    private boolean isReserved;
    private ParkingStatus status;
    private LocalDateTime lastUpdated;
}
