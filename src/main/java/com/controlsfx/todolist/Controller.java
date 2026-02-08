package com.controlsfx.todolist;

import com.controlsfx.todolist.entities.TodoData;
import com.controlsfx.todolist.entities.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
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

    @FXML
    private BorderPane mainBorderPane;

    private List<TodoItem> todoItems;


    public void initialize(){

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

        todoListView.getItems().setAll(TodoData.getInstance().getTodoItems());
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();
    }

    @FXML
    public void showNewItemDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        try{
            Parent root = FXMLLoader.load(getClass().getResource("todoItemDialog.fxml"));
            dialog.getDialogPane().setContent(root);

        }catch(Exception e){
            System.out.println("Couldn't load the dialog.");
            e.printStackTrace();
            return;
        }
    }

    @FXML
    public void handleClickListView(){
        TodoItem item = (TodoItem) todoListView.getSelectionModel().getSelectedItem();
        StringBuilder sb = new StringBuilder(item.getDetails());
        itemsDetailTextArea.setText(item.getDetails());
        deadlineLabel.setText(item.getDeadline().toString());
    }

}
