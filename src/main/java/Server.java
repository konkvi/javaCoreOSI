import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args){
        // создаем сервер с использованием ServerSocket, запускаем сервер на определенном порту и принимаем соединение
        // порт можно выбрать любой в доступном диапазоне 0-65536, рекомендуем использовать около 8080
        int port = 8089;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept(); // ждем подключения
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println("Hi. Write your name");
            final String userName = in.readLine();

            out.println(String.format("Hi %s, Write your second name/surname", userName));
            final String userSurname = in.readLine();

            out.println(String.format("Hi %s %s, Are you child? (yes/no)", userName, userSurname));
            final String answer = in.readLine();

            if (answer.equals("yes")){
                out.println(String.format("Welcome to the kids area! Let's play! /end", userName, userSurname));
            } else {
                out.println(String.format("Welcome to the adult zone! Have a good rest, or a good working day! /end", userName, userSurname));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
