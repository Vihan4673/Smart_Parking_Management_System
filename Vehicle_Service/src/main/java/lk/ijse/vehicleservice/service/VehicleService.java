package lk.ijse.vehicleservice.service;

import lk.ijse.vehicleservice.entity.Vehicle;
import lk.ijse.vehicleservice.repo.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle registerVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getVehiclesByUserId(Long userId) {
        return vehicleRepository.findByUserId(userId);
    }

    public Optional<Vehicle> getVehicleById(Long vehicleId) {
        return vehicleRepository.findById(vehicleId);
    }

    public Vehicle updateVehicle(Long vehicleId, Vehicle vehicle) {
        // implement update logic
        return null;
    }

    public void deleteVehicle(Long vehicleId) {
        vehicleRepository.deleteById(vehicleId);
    }

    public void vehicleEntry(Long vehicleId) {
        // implement entry simulation logic
    }

    public void vehicleExit(Long vehicleId) {
        // implement exit simulation logic
    }
}
