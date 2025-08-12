package xyz.vinllage.event.services;

import org.springframework.stereotype.Service;
import xyz.vinllage.event.entities.Event;
import xyz.vinllage.event.repositories.EventRepository;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event get(Long id) {
        return eventRepository.findById(id).orElseThrow();
    }

    public void saveAll(List<Event> events) {
        eventRepository.saveAll(events);
    }
}