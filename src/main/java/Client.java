import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) throws UnknownHostException {
        // String host= "localhost";
        String host = "netology.homework";
        int port= 8089;

        // определяем IP адрес программно:
        InetAddress inetAddress = InetAddress.getByName(host);
        System.out.println(host + ", ip address: " + inetAddress.getHostAddress());

        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            while (true) {
                String resp = in.readLine();
                System.out.println(resp);

                if (resp.contains("Write your name")) {
                    resp = "Stas";
                } else if (resp.contains("Write your second name/surname")){
                    resp = "Konkvi";
                }else if (resp.contains("Are you child? (yes/no)")){
                    resp = "no";
                } else if (resp.contains("/end")){
                    break;
                }
                System.out.println(" " + resp);
                out.println(resp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
