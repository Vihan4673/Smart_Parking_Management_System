package lk.ijse.parking_space_service.controller;

import lk.ijse.parking_space_service.entity.Reservations;
import lk.ijse.parking_space_service.entity.Reservations.Status;
import lk.ijse.parking_space_service.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationsService;

    @PostMapping
    public ResponseEntity<Reservations> createReservation(@RequestBody Reservations reservation) {
        Reservations created = reservationsService.createReservation(reservation);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reservations>> getReservationsByUserId(@PathVariable Long userId) {
        List<Reservations> reservations = reservationsService.getReservationsByUserId(userId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/space/{spaceId}")
    public ResponseEntity<List<Reservations>> getReservationsBySpaceId(@PathVariable Long spaceId) {
        List<Reservations> reservations = reservationsService.getReservationsBySpaceId(spaceId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<Reservations> getReservationById(@PathVariable Long reservationId) {
        Optional<Reservations> reservation = reservationsService.getReservationById(reservationId);
        return reservation.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{reservationId}")
    public ResponseEntity<Reservations> updateReservation(@PathVariable Long reservationId, @RequestBody Reservations reservation) {
        Reservations updated = reservationsService.updateReservation(reservationId, reservation);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long reservationId) {
        reservationsService.deleteReservation(reservationId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{reservationId}/status")
    public ResponseEntity<Reservations> updateReservationStatus(@PathVariable Long reservationId, @RequestParam Status status) {
        Reservations updated = reservationsService.updateReservationStatus(reservationId, status);
        return ResponseEntity.ok(updated);
    }
}
