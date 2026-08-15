package lk.ijse.userservice.controller;

import lk.ijse.userservice.entity.Logs;
import lk.ijse.userservice.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/logs")
public class LogsController {

    @Autowired
    private LogsService logsService;

    @PostMapping
    public ResponseEntity<Logs> createLog(@RequestBody Logs log) {
        Logs created = logsService.createLog(log);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<Logs>> getAllLogs() {
        List<Logs> logs = logsService.getAllLogs();
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Logs>> getLogsByUserId(@PathVariable Long userId) {
        List<Logs> logs = logsService.getLogsByUserId(userId);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/{logId}")
    public ResponseEntity<Logs> getLogById(@PathVariable Long logId) {
        Optional<Logs> log = logsService.getLogById(logId);
        return log.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{logId}")
    public ResponseEntity<Void> deleteLog(@PathVariable Long logId) {
        logsService.deleteLog(logId);
        return ResponseEntity.noContent().build();
    }
}
