package com.jeyanth.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BoxBreathing {
    private Timeline breathingCycle;
    private Timeline countdownTimer;
    private Label instruction = new Label("Ready to breathe");
    private Label timeLeft = new Label("");
    private Button startBtn = new Button("▶️ Start");
    private Button stopBtn = new Button("⏹ Stop");
    private ComboBox<String> durationSelect = new ComboBox<>();

    private int secondsRemaining = 0;

    public VBox getView() {
        Rectangle box = new Rectangle(160, 160, Color.TEAL);
        instruction.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        timeLeft.setStyle("-fx-font-size: 16px; -fx-text-fill: #555;");

        durationSelect.getItems().addAll("5 minutes", "10 minutes");
        durationSelect.setValue("5 minutes");

        startBtn.setOnAction(e -> startBreathing());
        stopBtn.setOnAction(e -> stopBreathing());

        StackPane animationPane = new StackPane(box, instruction);
        animationPane.setStyle("-fx-padding: 30;");

        VBox layout = new VBox(15, durationSelect, animationPane, timeLeft, new HBox(10, startBtn, stopBtn));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 20;");
        return layout;
    }

    private void startBreathing() {
        instruction.setText("🫁 Inhale");
        secondsRemaining = durationSelect.getValue().startsWith("5") ? 300 : 600;
        timeLeft.setText(formatTime(secondsRemaining));

        breathingCycle = new Timeline(
            new KeyFrame(Duration.seconds(0), e -> instruction.setText("🫁 Inhale")),
            new KeyFrame(Duration.seconds(4), e -> instruction.setText("⏸️ Hold")),
            new KeyFrame(Duration.seconds(8), e -> instruction.setText("😮‍💨 Exhale")),
            new KeyFrame(Duration.seconds(12), e -> instruction.setText("⏸️ Hold"))
        );
        breathingCycle.setCycleCount(Timeline.INDEFINITE);
        breathingCycle.play();

        countdownTimer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            secondsRemaining--;
            timeLeft.setText(formatTime(secondsRemaining));
            if (secondsRemaining <= 0) stopBreathing();
        }));
        countdownTimer.setCycleCount(Timeline.INDEFINITE);
        countdownTimer.play();
    }

    private void stopBreathing() {
        if (breathingCycle != null) breathingCycle.stop();
        if (countdownTimer != null) countdownTimer.stop();
        instruction.setText("Session stopped");
    }

    private String formatTime(int totalSec) {
        int min = totalSec / 60;
        int sec = totalSec % 60;
        return String.format("🕒 %02d:%02d remaining", min, sec);
    }
}
