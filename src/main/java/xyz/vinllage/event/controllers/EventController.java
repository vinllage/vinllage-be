package xyz.vinllage.event.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.vinllage.event.entities.Event;
import xyz.vinllage.event.services.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> list() {
        return eventService.findAll();
    }

    @GetMapping("/{id}")
    public Event detail(@PathVariable Long id) {
        return eventService.get(id);
    }
}
