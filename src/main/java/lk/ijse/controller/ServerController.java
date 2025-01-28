package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {
    @FXML
    private TextArea textArea;

    @FXML
    private TextField txtMessage;

    @FXML
    private Button btnSend;


    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String message = "";

    @FXML
    public void initialize() {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(4000);
                textArea.appendText("Server Started...\n");

                socket = serverSocket.accept();
                textArea.appendText("\nClient Accepted!\n");

                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                while (!message.equals("exit")){
                    message = dataInputStream.readUTF();
                    textArea.appendText("\n Client: "+message);
                }

            } catch (IOException e) {
                throw new RuntimeException();
            }
        }).start();
    }

    @FXML
    void btnSendOnAction(ActionEvent event) {
        try {
            dataOutputStream.writeUTF(txtMessage.getText().trim());
            dataOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @FXML
    void txtMessageOnAction(ActionEvent event) {
        btnSend.requestFocus();
    }

}
