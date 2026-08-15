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

    public Reservations createReservation(Reservations reservation) {
        return reservationsRepository.save(reservation);
    }
    public List<Reservations> getReservationsByUserId(Long userId) {
        return reservationsRepository.findByUserId(userId);
    }
    public List<Reservations> getReservationsBySpaceId(Long spaceId) {
        return reservationsRepository.findBySpaceId(spaceId);
    }

    public Optional<Reservations> getReservationById(Long reservationId) {
        return reservationsRepository.findById(reservationId);
    }


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
    public void deleteReservation(Long reservationId) {
        reservationsRepository.deleteById(reservationId);
    }
    public Reservations updateReservationStatus(Long reservationId, Status status) {
        return reservationsRepository.findById(reservationId).map(reservation -> {
            reservation.setStatus(status);
            return reservationsRepository.save(reservation);
        }).orElseThrow(() -> new RuntimeException("Reservation not found with id " + reservationId));
    }
}
