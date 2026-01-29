package com.controlsfx.todolist;

import com.controlsfx.todolist.entities.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private ListView<TodoItem> todoListView;

    @FXML
    private TextArea itemsDetailTextArea;

    @FXML
    private Label deadlineLabel;

    private List<TodoItem> todoItems;


    public void initialize(){
        TodoItem item1 = new TodoItem("Main birthday card",
                "Buy a 30th birthday card for John",
                LocalDate.of(2016, Month.APRIL, 25));

        TodoItem item2 = new TodoItem("Doctor's appointment",
                "See Dr. Smith at 123 Main Street. Bring paperwork.",
                LocalDate.of(2016, Month.MAY, 23));

        TodoItem item3 = new TodoItem("Finish design proposal for client",
                "I'd promised Mike I'd email website mockups by 22nd April.",
                LocalDate.of(2016, Month.APRIL, 22));

        TodoItem item4 = new TodoItem("Pick up Doug at the train station.",
                "Doug is arriving on March 23 on the 5:00 train.",
                LocalDate.of(2016, Month.MARCH, 25));

        TodoItem item5 = new TodoItem("Pick up dry cleaning",
                "The clothes should be ready by Wednesday.",
                LocalDate.of(2016, Month.APRIL, 20));

        todoItems = new ArrayList<TodoItem>();
        todoItems.add(item1);
        todoItems.add(item2);
        todoItems.add(item3);
        todoItems.add(item4);
        todoItems.add(item5);

        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observableValue, TodoItem oldValue,
                                TodoItem newValue) {
                if(newValue != null){
                    TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                    itemsDetailTextArea.setText(item.getDetails());
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
                    deadlineLabel.setText(df.format(item.getDeadline()));
                }
            }
        });

        todoListView.getItems().setAll(todoItems);
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();
    }

    @FXML
    public void handleClickListView(){
        TodoItem item = (TodoItem) todoListView.getSelectionModel().getSelectedItem();
        StringBuilder sb = new StringBuilder(item.getDetails());
        itemsDetailTextArea.setText(item.getDetails());
        deadlineLabel.setText(item.getDeadline().toString());
    }

}
