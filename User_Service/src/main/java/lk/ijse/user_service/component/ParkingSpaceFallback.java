package lk.ijse.user_service.component;

import lk.ijse.user_service.dto.ReservationDTO;
import lk.ijse.user_service.util.ParkingSpaceClient;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ParkingSpaceFallback implements ParkingSpaceClient {

    @Override
    public List<ReservationDTO> getReservationsByUser(Long userId) {
        // Fallback logic
        return Collections.emptyList(); // or cache data, or throw custom exception
    }
}
