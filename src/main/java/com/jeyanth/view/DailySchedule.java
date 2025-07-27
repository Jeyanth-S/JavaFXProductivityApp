package com.jeyanth.view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class DailySchedule {
    public VBox getView() {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20;");

        DayOfWeek today = LocalDate.now().getDayOfWeek();

        if (today == DayOfWeek.SUNDAY) {
            layout.getChildren().addAll(
                new Label("→ 6:00 — Wake Up"),
                new Label("→ 6:00–6:30 — Brush / Tea / Prayer / Exercise (10 mins)"),
                new Label("→ 6:30–7:00 — Freshup"),
                new Label("→ 7:00–7:30 — Breakfast"),
                new Label("→ 7:30–10:00 — Dev Flow (Read, Code, Reflect)"),
                new Label("→ 10:00–10:30 — Tea Break ☕"),
                new Label("→ 10:30–12:00 — Learning Session (AI/ML or Concepts)"),
                new Label("→ 12:00–12:30 — Lunch 🍽️"),
                new Label("→ 12:30–2:30 — Chill / Short game / YouTube / Music"),
                new Label("→ 2:30–4:30 — Deep Work (Coding, Reading, Writing)"),
                new Label("→ 4:30–6:30 — Walk / Stretch / Journal or organize workspace"),
                new Label("→ 7:00–7:30 — Dinner"),
                new Label("→ 7:30–10:30 — Explore / Learn / Strategy plan for week"),
                new Label("→ 10:30–11:30 — Wind down / Sleep prep"),
                new Label("→ Sleep 😴")
            );
        } else {
            layout.getChildren().addAll(
                new Label("→ 6:00 — Wake Up"),
                new Label("→ 6:00–6:30 — Brush / Tea / Prayer / Exercise (10 mins)"),
                new Label("→ 6:30–7:00 — Freshup"),
                new Label("→ 7:00–7:30 — Breakfast"),
                new Label("→ 7:30–8:30 — Prepare for college"),
                new Label("→ 8:30–3:00 — College"),
                new Label("→ 3:00–7:00 — Dev Flow (Java, AI/ML, etc.)"),
                new Label("→ 7:00–7:30 — Dinner"),
                new Label("→ 7:30–10:30 — Explore / Reflect / Practice"),
                new Label("→ 10:30–11:30 — Wind down / Sleep prep"),
                new Label("→ Sleep 😴")
            );
        }

        return layout;
    }
}
