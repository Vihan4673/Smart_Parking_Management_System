package lk.ijse.parking_space_service.controller;

import lk.ijse.parking_space_service.entity.ParkingZones;
import lk.ijse.parking_space_service.entity.ParkingSpace;
import lk.ijse.parking_space_service.service.ParkingZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zones")
@RequiredArgsConstructor
public class ParkingZoneController {

    private final ParkingZoneService parkingZoneService;

    @PostMapping
    public ResponseEntity<ParkingZones> createZone(@RequestBody ParkingZones zone) {
        ParkingZones createdZone = parkingZoneService.createZone(zone);
        return new ResponseEntity<>(createdZone, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ParkingZones>> getAllZones() {
        List<ParkingZones> zones = parkingZoneService.getAllZones();
        return ResponseEntity.ok(zones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingZones> getZoneById(@PathVariable Long id) {
        return parkingZoneService.getZoneById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingZones> updateZone(@PathVariable Long id, @RequestBody ParkingZones zone) {
        ParkingZones updatedZone = parkingZoneService.updateZone(id, zone);
        return ResponseEntity.ok(updatedZone);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZone(@PathVariable Long id) {
        parkingZoneService.deleteZone(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/spaces")
    public ResponseEntity<List<ParkingSpace>> getParkingSpacesInZone(@PathVariable Long id) {
        List<ParkingSpace> spaces = parkingZoneService.getParkingSpacesInZone(id);
        return ResponseEntity.ok(spaces);
    }
}