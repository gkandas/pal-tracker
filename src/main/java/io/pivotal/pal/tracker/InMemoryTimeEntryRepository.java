package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
private  long timeEntryId=0;
private HashMap<Long,TimeEntry> timeEntryMap=new HashMap<Long,TimeEntry>();
    @Override
    public TimeEntry create(TimeEntry entry) {
        TimeEntry copy = new TimeEntry(++timeEntryId,
                entry.getProjectId(), entry.getUserId(), entry.getDate(),
                entry.getHours());
        timeEntryMap.put(copy.getId(),copy);
        return copy;
    }

    @Override
    public TimeEntry find(long id) {
        return timeEntryMap.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        List<TimeEntry> timeEntryList=new ArrayList<TimeEntry>();
        for(TimeEntry timeEntry:timeEntryMap.values())
            timeEntryList.add(timeEntry);
        return timeEntryList;
    }

    @Override
    public TimeEntry update(long id, TimeEntry entry) {
        if(timeEntryMap.get(id)!=null) {
            TimeEntry copy = new TimeEntry(id,
                    entry.getProjectId(), entry.getUserId(), entry.getDate(),
                    entry.getHours());
            timeEntryMap.put(id, copy);
            return timeEntryMap.get(id);
        }
        else
        {
            return null;
        }
    }

    @Override
    public void delete(long id) {
        timeEntryMap.remove(id);
    }
}
