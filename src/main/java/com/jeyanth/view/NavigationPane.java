package com.jeyanth.view;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class NavigationPane {
    public VBox getView(Runnable showSchedule, Runnable showTasks, Runnable showBreathing) {
        VBox nav = new VBox(15);
        nav.setStyle("-fx-background-color: #2c3e50; -fx-padding: 20;");

        Button scheduleBtn = new Button("🗓 Daily Schedule");
        Button tasksBtn = new Button("✅ Task Tracker");
        Button breathingBtn = new Button("🌬 Breathing Room");

        scheduleBtn.setPrefWidth(160);
        tasksBtn.setPrefWidth(160);
        breathingBtn.setPrefWidth(160);

        scheduleBtn.setOnAction(e -> showSchedule.run());
        tasksBtn.setOnAction(e -> showTasks.run());
        breathingBtn.setOnAction(e -> showBreathing.run());

        nav.getChildren().addAll(scheduleBtn, tasksBtn, breathingBtn);
        return nav;
    }
}
