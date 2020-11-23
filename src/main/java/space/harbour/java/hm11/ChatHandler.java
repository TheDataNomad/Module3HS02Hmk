package space.harbour.java.hm11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import org.bson.Document;

public class ChatHandler extends Thread {
    Socket client;
    ChatServer server;
    PrintWriter out;
    MongoExecutor executor;

    public ChatHandler(ChatServer server, Socket client) {
        this.server = server;
        this.client = client;
        this.executor = new MongoExecutor();

    }

    public void run() {

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(client.getInputStream()))) {
            out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
            out.println("Enter a username: ");
            String name = in.readLine();
            setName(name);

            while (true) {
                out.println("[" + name + "]: ");
                String str = in.readLine();
                System.out.println("\n" + str);
                Date date = java.util.Calendar.getInstance().getTime();
                Document message = new Document("message", name + ": " + str)
                        .append("time", date.toString());
                executor.execStoreMessage(message);
                server.broadcast(name + ": " + str);
                if (str == null) {
                    break;
                }
                out.flush();
                if (str.toLowerCase().contains("bye")) {
                    server.clientDisconnected(this);
                    break;
                }
            }
            in.close();
            out.close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getMessageHistory() {
        for (Document message : executor.execGetAllMessages(x -> x)) {
            out.println("message.toJson()");
        }
    }

    public void sendMessage(String message) {
        out.println(message);
        out.flush();
    }
}