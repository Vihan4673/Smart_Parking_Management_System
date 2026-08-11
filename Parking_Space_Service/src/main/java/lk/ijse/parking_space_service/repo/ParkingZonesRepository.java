package lk.ijse.parking_space_service.repo;

import lk.ijse.parking_space_service.entity.ParkingZones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingZonesRepository extends JpaRepository<ParkingZones,Long> {
}
