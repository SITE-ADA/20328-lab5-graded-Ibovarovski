import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.w3c.dom.events.Event;

@Override
public List<Event> getEventsByTag(String tag) {
    if (tag == null || tag.isBlank()) {
        return List.of();
    }

    return eventRepository.findAll()
            .stream()
            .filter(event -> event.getTags() != null &&
                    event.getTags().stream()
                            .anyMatch(t -> t.equalsIgnoreCase(tag)))
            .collect(Collectors.toList());
}

@Override
public List<Event> getUpcomingEvents() {
    LocalDate today = LocalDate.now();

    return eventRepository.findAll()
            .stream()
            .filter(event -> event.getDate() != null &&
                    event.getDate().isAfter(today))
            .collect(Collectors.toList());
}

@Override
public List<Event> getEventsByPriceRange(double minPrice, double maxPrice) {
    if (minPrice < 0 || maxPrice < 0 || minPrice > maxPrice) {
        return List.of();
    }

    return eventRepository.findAll()
            .stream()
            .filter(event -> event.getPrice() >= minPrice &&
                    event.getPrice() <= maxPrice)
            .collect(Collectors.toList());
}

@Override
public List<Event> getEventsByDateRange(LocalDate startDate, LocalDate endDate) {
    if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
        return List.of();
    }

    return eventRepository.findAll()
            .stream()
            .filter(event -> event.getDate() != null &&
                    (event.getDate().isEqual(startDate) || event.getDate().isAfter(startDate)) &&
                    (event.getDate().isEqual(endDate) || event.getDate().isBefore(endDate)))
            .collect(Collectors.toList());
}
@Override
public Event updateEventPrice(Long eventId, double newPrice) {
    if (eventId == null || newPrice < 0) {
        return null;
    }

    Event event = eventRepository.findById(eventId).orElse(null);

    if (event == null) {
        return null;
    }

    event.setPrice(newPrice);
    event.setPrice(newPrice);
    return eventRepository.save(event);
}

