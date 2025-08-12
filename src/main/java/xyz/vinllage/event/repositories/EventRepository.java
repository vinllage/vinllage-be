package xyz.vinllage.event.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.vinllage.event.entities.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
