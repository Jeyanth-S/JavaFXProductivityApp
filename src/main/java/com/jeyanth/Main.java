package com.jeyanth;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.jeyanth.view.DailySchedule;
import com.jeyanth.view.NavigationPane;
import com.jeyanth.view.TaskTracker;
import com.jeyanth.db.DBInitializer;
import com.jeyanth.model.TaskService;
import com.jeyanth.view.BoxBreathing;

public class Main extends Application {
    // @Override
    // public void start(Stage stage) {
    //     DBInitializer.init();
    //     BorderPane root = new BorderPane();
    //     MenuBar menuBar = new MenuBar();
    //     Menu menu = new Menu("Navigate");

    //     MenuItem dailySchedule = new MenuItem("Daily Schedule");
    //     MenuItem taskTracker = new MenuItem("Task Tracker");
    //     MenuItem boxBreathing = new MenuItem("Box Breathing");

    //     menu.getItems().addAll(dailySchedule, taskTracker, boxBreathing);
    //     menuBar.getMenus().add(menu);
    //     root.setTop(menuBar);

    //     // Default view
    //     root.setCenter(new DailySchedule().getView());

    //     dailySchedule.setOnAction(e -> root.setCenter(new DailySchedule().getView()));
    //     taskTracker.setOnAction(e -> root.setCenter(new TaskTracker().getView()));
    //     boxBreathing.setOnAction(e -> root.setCenter(new BoxBreathing().getView()));


    //     stage.setOnCloseRequest(e -> {
    //         TaskService.flushToDatabase();
    //     });

    //     Scene scene = new Scene(root, 900, 600);
    //     scene.getStylesheets().add("/style.css");
    //     stage.setScene(scene);
    //     stage.setTitle("Jeyanth's Productivity Cockpit");
    //     stage.show();
    // }
    @Override
    public void start(Stage stage) {
        DBInitializer.init();

        BorderPane root = new BorderPane();

        VBox sidebar = new NavigationPane().getView(
            () -> root.setCenter(new DailySchedule().getView()),
            () -> root.setCenter(new TaskTracker().getView()),
            () -> root.setCenter(new BoxBreathing().getView())
        );

        root.setLeft(sidebar);
        root.setCenter(new DailySchedule().getView());

        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add("/style.css");
        stage.setScene(scene);
        stage.setTitle("Jeyanth's Productivity Cockpit");

        stage.setOnCloseRequest(e -> {System.out.println("👋 App closed. All tasks are already synced with DB.");});

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
