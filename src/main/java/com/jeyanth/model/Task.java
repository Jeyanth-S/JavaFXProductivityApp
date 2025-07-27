package com.jeyanth.model;

import javafx.beans.property.*;

public class Task {
    private final StringProperty task = new SimpleStringProperty();
    private final BooleanProperty done = new SimpleBooleanProperty();

    public Task(String name, boolean status) {
        task.set(name);
        done.set(status);
    }

    public StringProperty taskProperty() {
        return task;
    }

    public BooleanProperty doneProperty() {
        return done;
    }

    public String getTask() {
        return task.get();
    }

    public boolean isDone() {
        return done.get();
    }
}
