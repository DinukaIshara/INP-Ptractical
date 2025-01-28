package lk.ijse.clients;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Clients {

    public void clientAdd(){

        Stage stages = new Stage();

            try {
                stages.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/client_form.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stages.setTitle("Client Form");
            stages.centerOnScreen();

            stages.show();



    }
}
