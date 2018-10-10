import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    PrintWriter printWriter;
    BufferedReader bufferedReader;

    public Client(String host,int port) {
        try {
            System.out.println("Conectado no endereço " + host + " na porta " + port);
            Socket socket = null;
             printWriter = null;
             bufferedReader = null;

            try {
                socket = new Socket(host, port);
                printWriter = new PrintWriter(socket.getOutputStream(), true);
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (UnknownHostException e) {
                System.err.println("Host desconhecido: " + host);
                System.exit(1);
            } catch (IOException io) {
                System.err.println("Sem dados recebidos do servidor");
                System.exit(1);
            }
            if (generateRandomNumber() >= 0.5) {
                sendMessage();
            } else {

            }
            printWriter.close();
            bufferedReader.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double generateRandomNumber() {
        Random random = new Random();
        double randomNumber = (double) random.nextDouble() * 1;
        return randomNumber;
    }


    private void sendMessage() {
        System.out.println("Tô no print");
        printWriter.println("Tô");

    }
}

