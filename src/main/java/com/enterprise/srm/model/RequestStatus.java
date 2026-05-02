package com.enterprise.srm.model;

import java.util.Set;

public enum RequestStatus {
    NEW("New", Set.of("ASSIGNED")),
    ASSIGNED("Assigned", Set.of("IN_PROGRESS", "WAITING_FOR_INFO")),
    IN_PROGRESS("In Progress", Set.of("WAITING_FOR_INFO", "RESOLVED")),
    WAITING_FOR_INFO("Waiting for Info", Set.of("IN_PROGRESS", "RESOLVED")),
    RESOLVED("Resolved", Set.of("CLOSED", "IN_PROGRESS")),
    CLOSED("Closed", Set.of());

    private final String displayName;
    private final Set<String> allowedTransitions;

    RequestStatus(String displayName, Set<String> allowedTransitions) {
        this.displayName = displayName;
        this.allowedTransitions = allowedTransitions;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean canTransitionTo(RequestStatus target) {
        return allowedTransitions.contains(target.name());
    }

    public Set<String> getAllowedTransitions() {
        return allowedTransitions;
    }
}
