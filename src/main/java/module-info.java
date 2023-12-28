module Tictactoe {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;

    opens org.demo.Application.controller to javafx.graphics,javafx.fxml,javafx.base,javafx.controls;
    opens org.demo.Application to javafx.graphics,javafx.fxml,javafx.base,javafx.controls;
    exports org.demo.Application.controller;
    exports org.demo.Application;
}
