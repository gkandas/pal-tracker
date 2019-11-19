package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {
private TimeEntryRepository timeEntryRepository;
    public TimeEntryController(TimeEntryRepository timeEntryRepository){
this.timeEntryRepository=timeEntryRepository;
    }
    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry) {
        return ResponseEntity.status(HttpStatus.CREATED).body(timeEntryRepository.create(timeEntry));
    }
    @GetMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable  long timeEntryId) {
        TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);
        if (timeEntry == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return  ResponseEntity.ok(timeEntry);
    }
    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(timeEntryRepository.list());
    }

    @PutMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId, @RequestBody TimeEntry timeEntryToUpdate) {
        TimeEntry timeEntry = timeEntryRepository.update(timeEntryId, timeEntryToUpdate);
        if (timeEntry == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(timeEntry);
    }
    @DeleteMapping("{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
