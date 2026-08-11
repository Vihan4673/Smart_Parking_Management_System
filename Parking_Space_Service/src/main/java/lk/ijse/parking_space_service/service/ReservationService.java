package lk.ijse.parking_space_service.service;

import lk.ijse.parking_space_service.entity.Reservations;
import lk.ijse.parking_space_service.repo.ReservationsRepository;
import lk.ijse.parking_space_service.entity.Reservations.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationsRepository reservationsRepository;

    // POST /api/reservations - Reserve a parking spot
    public Reservations createReservation(Reservations reservation) {
        return reservationsRepository.save(reservation);
    }

    // GET /api/reservations/user/{userId} - Get all reservations for a user
    public List<Reservations> getReservationsByUserId(Long userId) {
        return reservationsRepository.findByUserId(userId);
    }

    // GET /api/reservations/space/{spaceId} - Get reservations for a parking space
    public List<Reservations> getReservationsBySpaceId(Long spaceId) {
        return reservationsRepository.findBySpaceId(spaceId);
    }

    // GET /api/reservations/{reservationId} - Get a specific reservation
    public Optional<Reservations> getReservationById(Long reservationId) {
        return reservationsRepository.findById(reservationId);
    }

    // PUT /api/reservations/{reservationId} - Update reservation
    public Reservations updateReservation(Long reservationId, Reservations updatedReservation) {
        return reservationsRepository.findById(reservationId).map(reservation -> {
            reservation.setUserId(updatedReservation.getUserId());
            reservation.setVehicleId(updatedReservation.getVehicleId());
            reservation.setSpaceId(updatedReservation.getSpaceId());
            reservation.setStartTime(updatedReservation.getStartTime());
            reservation.setEndTime(updatedReservation.getEndTime());
            reservation.setStatus(updatedReservation.getStatus());
            return reservationsRepository.save(reservation);
        }).orElseThrow(() -> new RuntimeException("Reservation not found with id " + reservationId));
    }

    // DELETE /api/reservations/{reservationId} - Cancel reservation
    public void deleteReservation(Long reservationId) {
        reservationsRepository.deleteById(reservationId);
    }

    // PATCH /api/reservations/{id}/status - Update reservation status
    public Reservations updateReservationStatus(Long reservationId, Status status) {
        return reservationsRepository.findById(reservationId).map(reservation -> {
            reservation.setStatus(status);
            return reservationsRepository.save(reservation);
        }).orElseThrow(() -> new RuntimeException("Reservation not found with id " + reservationId));
    }
}
