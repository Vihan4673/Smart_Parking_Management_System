package lk.ijse.parking_space_service.repo;

import lk.ijse.parking_space_service.entity.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservations,Long> {
    List<Reservations> findByUserId(Long userId);
    List<Reservations> findBySpaceId(Long spaceId);

}
