import java.io.*;
import java.net.*;

public class Server{
    private ServerSocket socket;
    private Socket client;
    private InputStream in;
    private OutputStream os;
    private ObjectInputStream out;
    private ObjectOutputStream oos;
    public static final int PORT_NUMBER = 8081;

    public Server(){
        System.out.println("Iniciando o servidor");
        try{
            socket = new ServerSocket(PORT_NUMBER);
            client = socket.accept();
            String message = "Teste";
            System.out.println("Novo cliente conectado pelo endereço: "+socket.getInetAddress().getHostAddress());
            /*
            out = new ObjectInputStream(client.getInputStream());
            while((message = ((String) out.readObject())) != null){
                oos = new ObjectOutputStream(client.getOutputStream());
                oos.writeObject((Object)message);
            }
            */
            in = client.getInputStream();
            os = client.getOutputStream();
            os.write(message.getBytes());

            out.close();
        }catch (Exception exception){
            System.out.println("Não foi possível iniciar o servidor :" + exception.toString());
        }
    }

}
