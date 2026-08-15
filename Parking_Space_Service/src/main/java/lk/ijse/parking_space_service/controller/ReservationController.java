package lk.ijse.parking_space_service.controller;

import lk.ijse.parking_space_service.entity.Reservations;
import lk.ijse.parking_space_service.entity.Reservations.Status;
import lk.ijse.parking_space_service.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor // Field Injection වෙනුවට Constructor Injection සඳහා
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Reservations> createReservation(@RequestBody Reservations reservation) {
        Reservations created = reservationService.createReservation(reservation);
        // Resource එකක් Create කරද්දී HTTP 201 Created Status code එකක් Return කිරීම REST Best Practice එකකි
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reservations>> getReservationsByUserId(@PathVariable Long userId) {
        List<Reservations> reservations = reservationService.getReservationsByUserId(userId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/space/{spaceId}")
    public ResponseEntity<List<Reservations>> getReservationsBySpaceId(@PathVariable Long spaceId) {
        List<Reservations> reservations = reservationService.getReservationsBySpaceId(spaceId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<Reservations> getReservationById(@PathVariable Long reservationId) {
        return reservationService.getReservationById(reservationId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{reservationId}")
    public ResponseEntity<Reservations> updateReservation(
            @PathVariable Long reservationId,
            @RequestBody Reservations reservation) {
        Reservations updated = reservationService.updateReservation(reservationId, reservation);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long reservationId) {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{reservationId}/status")
    public ResponseEntity<Reservations> updateReservationStatus(
            @PathVariable Long reservationId,
            @RequestParam Status status) {
        Reservations updated = reservationService.updateReservationStatus(reservationId, status);
        return ResponseEntity.ok(updated);
    }
}