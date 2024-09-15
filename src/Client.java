import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

class Client implements Runnable {
    Socket socket;
    InputStream is;
    OutputStream os;
    ChatServer serv;
    PrintStream out;

    public Client(Socket socket, ChatServer serv) {
        this.socket = socket;
        this.serv = serv;
        new Thread(this).start();
    }

    public void run() {
        try {
            // получаем потоки ввода и вывода
            is = socket.getInputStream();
            os = socket.getOutputStream();

            // создаем удобные средства ввода и вывода
            Scanner in = new Scanner(is);
            out = new PrintStream(os);

            // читаем из сети и пишем в сеть
            out.println("Welcome to chat!");
            String input = in.nextLine();
            while (!input.equals("bye")) {
                serv.printAll(input);
                input = in.nextLine();
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printMessage(String mess) {
        out.println(mess);
    }
}
