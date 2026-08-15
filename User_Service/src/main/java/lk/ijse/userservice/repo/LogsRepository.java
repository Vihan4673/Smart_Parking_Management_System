package lk.ijse.userservice.repo;

import lk.ijse.userservice.entity.Logs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogsRepository extends JpaRepository<Logs,Long> {
    List<Logs> findByUserId(Long userId);
}
