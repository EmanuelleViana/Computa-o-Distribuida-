import java.io.*;
import java.net.*;

public class Server{
    public static final int port = 8081;
    protected Socket socket;

    public Server(Socket socket){
        this.socket = socket;
        System.out.println("Nova conexão iniciada a partir do "+socket.getInetAddress());
        initServer();
    }

    private void initServer() {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(is));
            String messageRequest;
            while ((messageRequest = bf.readLine()) != null){
                System.out.println("Mensagem tratar aqui");
                messageRequest+="\n";
                os.write(messageRequest.getBytes());
            }
        }catch(IOException io){
        System.out.println("Sem dados recebidos do cliente");
        }finally {
            try {
           is.close();
           os.close();
           socket.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
