package com.jeyanth.view;

import com.jeyanth.db.DBService;
import com.jeyanth.model.Task;
import com.jeyanth.model.TaskService;

import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class TaskTracker {
    public VBox getView() {
        TableView<Task> table = new TableView<>(FXCollections.observableArrayList(TaskService.getTasksForToday()));

        TableColumn<Task, String> taskCol = new TableColumn<>("Task");
        taskCol.setCellValueFactory(cell -> cell.getValue().taskProperty());

        TableColumn<Task, Boolean> doneCol = new TableColumn<>("Done");
        doneCol.setCellValueFactory(cell -> cell.getValue().doneProperty());

        doneCol.setCellFactory(col -> new TableCell<Task, Boolean>() {
            private final CheckBox checkBox = new CheckBox();

            {
                checkBox.setOnAction(e -> {
                    Task task = getTableView().getItems().get(getIndex());
                    boolean isChecked = checkBox.isSelected();
                    task.doneProperty().set(isChecked);
                    TaskService.updateTaskStatus(task.getTask(), isChecked);

                    if (isChecked) {
                        checkBox.setDisable(true); // Lock after marking done
                        checkBox.setStyle("-fx-opacity: 0.5;"); // Visual feedback
                    }
                });
            }

            @Override
            protected void updateItem(Boolean done, boolean empty) {
                super.updateItem(done, empty);
                if (empty || getIndex() >= getTableView().getItems().size()) {
                    setGraphic(null);
                } else {
                    checkBox.setSelected(done);
                    setGraphic(checkBox);
                    checkBox.setDisable(done); // Already done? Lock it!
                }
            }
        });



        table.getColumns().addAll(taskCol, doneCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox layout = new VBox(10, table);
        layout.setStyle("-fx-padding: 20;");
        return layout;
    }
}
