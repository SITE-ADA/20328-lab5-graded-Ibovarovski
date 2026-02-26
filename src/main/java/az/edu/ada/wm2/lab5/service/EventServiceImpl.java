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

