package Lesson_6.Server;

import java.io.IOException;
import java.util.logging.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Formatter;

public class MainServer {

    private Vector<ClientHandler> clients;
    private static final Logger logger = Logger.getLogger("");

    public MainServer() throws SQLException {
        ServerSocket server = null;
        Socket socket = null;
        clients = new Vector<>();

        logger.setLevel(Level.ALL);
        logger.getHandlers()[0].setLevel(Level.ALL);
        logger.getHandlers()[0].setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return record.getLevel() + "\t" + record.getMessage() + "\t" + record.getMillis() + "\n";
            }
        });

        try {
            AuthService.connect();

           // System.out.println(AuthService.getNickByLoginAndPass("login12", "pass1"));

            server = new ServerSocket(8189);
//            System.out.println("Сервер запущен");
            logger.log(Level.INFO,"Сервер запущен");

            while (true) {
                socket = server.accept();
//                System.out.println("Клиент подключился");
                logger.log(Level.INFO,"Клиент подключился");
                new ClientHandler(socket, this);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AuthService.disconnect();
        }
    }

    // подписываем клиента на рассылку
    public void subscribe(ClientHandler client) {
        clients.add(client);
    }

    // отписываем клиента от рассылки сообщений
    public void unsubscribe(ClientHandler client){
        clients.remove(client);
    }

    public void broadcastMsg(String msg) {
        for (ClientHandler o: clients) {
            o.sendMsg(msg);
        }
    }

    public void unicastMsg(String nick, String[] s) {
        // формируем строку сообщения
        StringJoiner str = new StringJoiner(" ");
        for (int i = 2; i < s.length; i++)
            str.add(s[i]);

        for (ClientHandler o: clients) {
            if (o.getNick().equals(s[1])){

                o.sendMsg(nick + ": " + str);
                break;
            }
        }
    }
}

