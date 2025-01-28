package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientController {
        @FXML
        private TextArea textArea;

        @FXML
        private TextField txtMessage;

        @FXML
        private Button btnSend;

        private Socket socket;
        private DataInputStream dataInputStream;
        private DataOutputStream dataOutputStream;
        private String message = "";

        @FXML
        public void initialize() {
            new Thread(() -> {
                try {
                    socket = new Socket("localhost", 4000);
                    textArea.appendText("\nClient Started");

                    dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    dataInputStream = new DataInputStream(socket.getInputStream());

                    while (!message.equals("exit")){
                        message = dataInputStream.readUTF();
                        textArea.appendText("\nServer: "+message);
                    }
                    socket.close();

                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }).start();
        }

        @FXML
        void btnSendOnAction(ActionEvent event) {
            try {
                dataOutputStream.writeUTF(txtMessage.getText());
                dataOutputStream.flush();
                message = txtMessage.getText();
                textArea.appendText("\nClient : "+message);
                txtMessage.clear();
            } catch (IOException e) {
                throw new RuntimeException();
            }

        }

        @FXML
        void txtMessageOnAction(ActionEvent event) {
            btnSendOnAction(event);
        }


}
