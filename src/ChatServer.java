import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    ArrayList<Client> clients = new ArrayList<>();
    ServerSocket server;

    public ChatServer() throws IOException {
        server = new ServerSocket(1234);
    }

    public void run() {
        while (true) {
            System.out.println("Waiting...");
            Socket socket;
            // ждем клиента из сети
            try {
               socket = server.accept();
                System.out.println("Client connected!");
                clients.add(new Client(socket, this));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new ChatServer().run();
    }
    public void printAll(String mess){
        for (Client cl: clients){
             cl.printMessage(mess);
        }
    }
}