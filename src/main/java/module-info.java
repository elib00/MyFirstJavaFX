module com.example.myfirstjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.myfirstjavafx to javafx.fxml;
    exports com.example.myfirstjavafx;
}