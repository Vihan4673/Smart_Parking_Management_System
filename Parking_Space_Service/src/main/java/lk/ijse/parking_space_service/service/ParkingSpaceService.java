package lk.ijse.parking_space_service.service;

import lk.ijse.parking_space_service.entity.ParkingSpace;
import lk.ijse.parking_space_service.repo.ParkingSpaceRepository;
import lk.ijse.parking_space_service.util.ParkingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSpaceService {

    @Autowired
    private ParkingSpaceRepository repository;

    public ParkingSpace createSpace(ParkingSpace space) {
        return repository.save(space);
    }
    public List<ParkingSpace> getAllSpaces() {
        return repository.findAll();
    }

    public List<ParkingSpace> filterByLocation(String location) {
        return repository.findByLocationDescription(location);
    }

    public ParkingSpace getSpaceById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Space not found"));
    }

    public ParkingSpace updateSpace(Long id, ParkingSpace updatedSpace) {
        return repository.findById(id).map(space -> {
            space.setOwnerId(updatedSpace.getOwnerId());
            space.setZoneId(updatedSpace.getZoneId());
            space.setLocationDescription(updatedSpace.getLocationDescription());
            space.setHourlyRate(updatedSpace.getHourlyRate());
            space.setIotDeviceId(updatedSpace.getIotDeviceId());
            return repository.save(space);
        }).orElseThrow(() -> new RuntimeException("Space not found"));
    }

    public void deleteSpace(Long id) {
        repository.deleteById(id);
    }

    public ParkingSpace updateStatus(Long id, ParkingStatus status) {
        ParkingSpace space = repository.findById(id).orElseThrow(() -> new RuntimeException("Space not found"));
        space.setStatus(status);
        space.setAvailable(status == ParkingStatus.AVAILABLE);
        return repository.save(space);
    }

    public ParkingSpace reserveSpot(Long id) {
        return updateStatus(id, ParkingStatus.RESERVED);
    }

    public ParkingSpace releaseSpot(Long id) {
        return updateStatus(id, ParkingStatus.AVAILABLE);
    }

    public ParkingSpace occupySpot(Long id) {
        return updateStatus(id, ParkingStatus.OCCUPIED);
    }
}
