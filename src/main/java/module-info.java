module com.controlsfx.todolist {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.controlsfx.todolist to javafx.fxml;
    exports com.controlsfx.todolist;
}