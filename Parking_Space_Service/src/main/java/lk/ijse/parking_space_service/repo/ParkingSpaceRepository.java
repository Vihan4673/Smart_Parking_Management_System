package lk.ijse.parking_space_service.repo;

import lk.ijse.parking_space_service.entity.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {
    List<ParkingSpace> findByLocationDescription(String location);
    List<ParkingSpace> findByZoneId(Long zoneId);
}
