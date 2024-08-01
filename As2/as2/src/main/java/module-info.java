module org.example.as2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens org.example.as2 to javafx.fxml;
    exports org.example.as2;
}