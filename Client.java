package Lesson3.Lesson3_4;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;

    public Client() {
        new Thread(() -> {
            try (Socket socket = new Socket(SERVER_ADDR, SERVER_PORT);
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

                Test objectToSend = new Test("Отправить с \"Клиента\"", "Получить на \"Сервер\"");
                out.writeObject(objectToSend);
                out.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        Client client = new Client();
    }
}

