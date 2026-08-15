package lk.ijse.parking_space_service.controller;

import lk.ijse.parking_space_service.entity.ParkingSpace;
import lk.ijse.parking_space_service.service.ParkingSpaceService;
import lk.ijse.parking_space_service.util.ParkingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spaces")
@RequiredArgsConstructor // Field Injection වෙනුවට Constructor Injection සඳහා
public class ParkingSpaceController {

    private final ParkingSpaceService service;

    @PostMapping
    public ResponseEntity<ParkingSpace> createSpace(@RequestBody ParkingSpace space) {
        ParkingSpace created = service.createSpace(space);
        // Created (201 Created) status code එකක් Return කිරීම REST Best Practice එකකි
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ParkingSpace>> getAllSpaces(@RequestParam(required = false) String location) {
        List<ParkingSpace> spaces;
        if (location != null && !location.trim().isEmpty()) {
            spaces = service.filterByLocation(location);
        } else {
            spaces = service.getAllSpaces();
        }
        return ResponseEntity.ok(spaces);
    }

    @GetMapping("/{spaceId}")
    public ResponseEntity<ParkingSpace> getSpaceById(@PathVariable Long spaceId) {
        ParkingSpace space = service.getSpaceById(spaceId);
        return ResponseEntity.ok(space);
    }

    @PutMapping("/{spaceId}")
    public ResponseEntity<ParkingSpace> updateSpace(@PathVariable Long spaceId, @RequestBody ParkingSpace space) {
        ParkingSpace updated = service.updateSpace(spaceId, space);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{spaceId}")
    public ResponseEntity<Void> deleteSpace(@PathVariable Long spaceId) {
        service.deleteSpace(spaceId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{spaceId}/status")
    public ResponseEntity<ParkingSpace> updateStatus(
            @PathVariable Long spaceId,
            @RequestParam ParkingStatus status) {
        ParkingSpace updated = service.updateStatus(spaceId, status);
        return ResponseEntity.ok(updated);
    }
}