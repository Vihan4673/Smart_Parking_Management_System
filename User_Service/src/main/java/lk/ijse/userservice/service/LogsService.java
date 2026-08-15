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

    public Logs createLog(Logs log) {
        return logsRepository.save(log);
    }

    public List<Logs> getAllLogs() {
        return logsRepository.findAll();
    }

    public List<Logs> getLogsByUserId(Long userId) {
        return logsRepository.findByUserId(userId);
    }

    public Optional<Logs> getLogById(Long logId) {
        return logsRepository.findById(logId);
    }

    public void deleteLog(Long logId) {
        logsRepository.deleteById(logId);
    }
}
