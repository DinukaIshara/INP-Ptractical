package lk.ijse.clients;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Clients {

    public void clientAdd(int num){

        Stage[] stages = new Stage[num];

        for (int i = 0; i < num; i++) {
            try {
                stages[i].setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/client_form.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stages[i].setTitle("Client Form");
            stages[i].centerOnScreen();

            stages[i].show();

        }

    }
}
