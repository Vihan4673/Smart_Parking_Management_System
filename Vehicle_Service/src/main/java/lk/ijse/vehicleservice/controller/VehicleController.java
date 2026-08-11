package lk.ijse.vehicleservice.controller;

import lk.ijse.vehicleservice.entity.Vehicle;
import lk.ijse.vehicleservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<Vehicle> registerVehicle(@RequestBody Vehicle vehicle) {
        Vehicle createdVehicle = vehicleService.registerVehicle(vehicle);
        return ResponseEntity.ok(createdVehicle);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Vehicle>> getVehiclesByUserId(@PathVariable Long userId) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByUserId(userId);
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long vehicleId) {
        Optional<Vehicle> vehicle = vehicleService.getVehicleById(vehicleId);
        return vehicle.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long vehicleId, @RequestBody Vehicle vehicle) {
        Vehicle updatedVehicle = vehicleService.updateVehicle(vehicleId, vehicle);
        return ResponseEntity.ok(updatedVehicle);
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/entry/{vehicleId}")
    public ResponseEntity<Void> vehicleEntry(@PathVariable Long vehicleId) {
        vehicleService.vehicleEntry(vehicleId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/exit/{vehicleId}")
    public ResponseEntity<Void> vehicleExit(@PathVariable Long vehicleId) {
        vehicleService.vehicleExit(vehicleId);
        return ResponseEntity.ok().build();
    }
}
