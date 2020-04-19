package Lesson3.Lesson3_4;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;

public class Server {
    private final int SERVER_PORT = 8189;

    public Server() {
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
             Socket socket = serverSocket.accept()) {
            System.out.println("Клиент подключился");

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Test objectToReceive = (Test) inputStream.readObject();
            inputStream.close();

        } catch (IOException |
                ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
    }
}
