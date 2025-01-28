package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.clients.Clients;

import java.io.IOException;

public class AddUserController {

    @FXML
    private TextField textUserName;

    @FXML
    private Button btnAddUser;

    @FXML
    void btnAddUserOnAction(ActionEvent event) {
        //Stage stage = new Stage();
        Clients clients = new Clients();
        clients.clientAdd(Integer.parseInt(textUserName.getText()));
        Stage stage = new Stage();

        try {
            stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/client_form.fxml"))));

            stage.setTitle("Server Form");

            stage.centerOnScreen();

            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
