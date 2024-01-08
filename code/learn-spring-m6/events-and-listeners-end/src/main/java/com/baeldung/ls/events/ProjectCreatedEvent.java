package com.baeldung.ls.events;

public class ProjectCreatedEvent {

    private String name;

    public ProjectCreatedEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
