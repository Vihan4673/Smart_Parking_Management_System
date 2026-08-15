package lk.ijse.userservice.service;

import lk.ijse.userservice.entity.Logs;
import lk.ijse.userservice.repo.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogsService {

    @Autowired
    private LogsRepository logsRepository;

    // POST /api/logs - Create a log entry
    public Logs createLog(Logs log) {
        return logsRepository.save(log);
    }

    // GET /api/logs - Get all logs (admin view)
    public List<Logs> getAllLogs() {
        return logsRepository.findAll();
    }

    // GET /api/logs/user/{userId} - Get logs for a specific user
    public List<Logs> getLogsByUserId(Long userId) {
        return logsRepository.findByUserId(userId);
    }

    // GET /api/logs/{logId} - Get a specific log entry by ID
    public Optional<Logs> getLogById(Long logId) {
        return logsRepository.findById(logId);
    }

    // DELETE /api/logs/{logId} - Delete a log entry (optional)
    public void deleteLog(Long logId) {
        logsRepository.deleteById(logId);
    }
}
