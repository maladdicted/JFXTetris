package com.grandrain.tetris.logic.events;

public record MoveEvent(EventSource eventSource) {

    public EventSource getEventSource() {
        return eventSource;
    }

}
