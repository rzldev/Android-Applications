import java.io.*;
import java.net.*;
import java.util.*;

public class MobiChatServer {

private static final int PORT_NUMBER = 8090;

private List<PrintWriter> clients;

/** Buat sebuah server. */
public void ChatServer() {
clients = new LinkedList<PrintWriter>();
}

/** Memulai server. */
public void start() {
System.out.println("Server Chat Mobil di buka pada port "
+ PORT_NUMBER + "!");
try {
ServerSocket s = new ServerSocket(PORT_NUMBER);
for (;;) {
Socket incoming = s.accept();
new ClientHandler(incoming).start();
}
} catch (Exception e) {
e.printStackTrace();
}
System.out.println("server stopped.");
}


private void addClient(PrintWriter out) {
synchronized(clients) {
clients.add(out);
}
}

/** Adds the client with given print writer. */
private void removeClient(PrintWriter out) {
synchronized(clients) {
clients.remove(out);
}
}

/** Broadcasts the given text to all clients. */
private void broadcast(String msg) {
for (PrintWriter out: clients) {
out.println(msg);
out.flush();
}
}

public static void main(String[] args) {
if (args.length > 0) {
System.out.println(USAGE);
System.exit(-1);
}
new ChatServer().start();
}

/**
* Class untuk hander Client
*/
private class ClientHandler extends Thread {

private Socket incoming;

/** Creates a hander to serve the client on the given socket. */
public ClientHandler(Socket incoming) {
this.incoming = incoming;
}
//broadcast pesan ..
public void run() {
PrintWriter out = null;
try {
out = new PrintWriter(
new OutputStreamWriter(incoming.getOutputStream()));

// inform the server of this new client
ChatServer.this.addClient(out);

out.print("Selamat Datang di Mobi Chat! ");
out.println("Tuliskan BYE untuk keluar");
out.flush();

BufferedReader in
= new BufferedReader(
new InputStreamReader(incoming.getInputStream()));
for (;;) {
String msg = in.readLine();
if (msg == null) {
break;
} else {
if (msg.trim().equals("BYE"))
break;
System.out.println("Pesan dari " + incoming.getRemoteSocketAddress().toString()+": " + msg);
// broadcast the receive message
ChatServer.this.broadcast(msg);
}
}
incoming.close();
ChatServer.this.removeClient(out);
} catch (Exception e) {
if (out != null) {
ChatServer.this.removeClient(out);
}
e.printStackTrace();
}
}
}
}

