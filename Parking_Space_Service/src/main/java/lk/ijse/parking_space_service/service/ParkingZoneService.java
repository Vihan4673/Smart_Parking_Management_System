package lk.ijse.parking_space_service.service;

import lk.ijse.parking_space_service.entity.ParkingZones;
import lk.ijse.parking_space_service.entity.ParkingSpace; // Assuming this entity exists
import lk.ijse.parking_space_service.repo.ParkingZonesRepository;
import lk.ijse.parking_space_service.repo.ParkingSpaceRepository; // Assuming this repo exists
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingZoneService {
    @Autowired
    private ParkingZonesRepository parkingZoneRepository;

    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;

    public ParkingZones createZone(ParkingZones zone) {
        return parkingZoneRepository.save(zone);
    }

    public List<ParkingZones> getAllZones() {
        return parkingZoneRepository.findAll();
    }

    public Optional<ParkingZones> getZoneById(Long id) {
        return parkingZoneRepository.findById(id);
    }

    public ParkingZones updateZone(Long id, ParkingZones zone) {
        return parkingZoneRepository.findById(id).map(existingZone -> {
            existingZone.setName(zone.getName());
            existingZone.setCity(zone.getCity());
            existingZone.setLatitude(zone.getLatitude());
            existingZone.setLongitude(zone.getLongitude());
            return parkingZoneRepository.save(existingZone);
        }).orElseThrow(() -> new RuntimeException("Parking zone not found with id " + id));
    }

    public void deleteZone(Long id) {
        parkingZoneRepository.deleteById(id);
    }

    public List<ParkingSpace> getParkingSpacesInZone(Long zoneId) {
        return parkingSpaceRepository.findByZoneId(zoneId);
    }
}
