package lk.ijse.userservice.util;

import lk.ijse.userservice.component.ParkingSpaceFallback;
import lk.ijse.userservice.dto.ReservationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "parking-space-service", fallback = ParkingSpaceFallback.class) // name must match spring.application.name
public interface ParkingSpaceClient {

    @GetMapping("/parking_space_service/api/reservations/user/{userId}")
    List<ReservationDTO> getReservationsByUser(@PathVariable Long userId);
}
