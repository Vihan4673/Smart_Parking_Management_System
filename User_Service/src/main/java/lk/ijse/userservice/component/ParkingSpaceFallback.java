package lk.ijse.userservice.component;

import lk.ijse.userservice.dto.ReservationDTO;
import lk.ijse.userservice.util.ParkingSpaceClient;
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
