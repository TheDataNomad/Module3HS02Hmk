package space.harbour.java.hm11;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {

    public ChatClient(String name, String server, int port) {
        try (Socket socket = new Socket(server, port);
                DataInputStream in = new DataInputStream(socket.getInputStream());
                BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter out = new PrintWriter(
                        new OutputStreamWriter(socket.getOutputStream()))) {
            out.println(name);
            out.flush();
            while (true) {
                // Blocking call, will wait here until there is data from server
                System.out.println(in.readUTF());
                // Another blocking call, will wait until user types something
                out.println(console.readLine());
                out.flush();
            }
        } catch (UnknownHostException e) {
            System.out.println("Server " + server + " not found");
        } catch (IOException e) {
            System.out.println("Lost connection to server " + server);
        }
    }

    public static void main(String[] args) {
        ChatClient client = new ChatClient("Nasser", "127.0.0.1", 8008);
    }
}