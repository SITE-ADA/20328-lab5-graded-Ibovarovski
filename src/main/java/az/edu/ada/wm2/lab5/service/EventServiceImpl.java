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
