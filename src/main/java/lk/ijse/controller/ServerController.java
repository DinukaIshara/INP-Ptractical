package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lk.ijse.clients.Clients;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {

    @FXML
    private TextField txtMessage;

    @FXML
    private TextField txtUser;

    @FXML
    private Button btnSend;


    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String message = "";

    //Scanner input = new Scanner(dataInputStream);


    private int user = 0;
    Clients[] clients;

    @FXML
    public void initialize() {
        new Thread(() -> {
            try {

//                System.out.println("Enter message : ");
//                message = input.next();

                serverSocket = new ServerSocket(4000);
                System.out.println("Server Started...\n");

                socket = serverSocket.accept();
                System.out.println("\nClient Accepted!\n");

                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                while (!message.equals("exit")){
                    message = dataInputStream.readUTF();
                    System.out.println("\nClient: "+message);
                }

            } catch (IOException e) {
                throw new RuntimeException();
            }
        }).start();
    }

    @FXML
    void btnSendOnAction(ActionEvent event) {
        try {
            /*user = Integer.parseInt(txtUser.getText());
            clients = new Clients[user];

            for (int i = 0; i < user; i++) {
                clients[i].clientAdd();
            }*/

            dataOutputStream.writeUTF(txtMessage.getText().trim());
            dataOutputStream.flush();
            message = txtMessage.getText();
            System.out.println("\nServer : "+message);
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
