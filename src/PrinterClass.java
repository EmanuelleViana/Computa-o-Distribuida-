import java.io.*;
import java.net.*;
import java.util.*;

public class PrinterClass {

    private static void initProgram(String[]args){
       if(args.length != 0){
           if(args[0].equalsIgnoreCase("server")){
               initServer();
           }else if(args[0].equalsIgnoreCase("client")){
                initClient();
           }else{
               System.out.println("Nenhuma parâmetro válido foi digitado");
           }
       }
    }

    private static  void initServer(){
        ServerSocket serverSocket = null;
        Server server;
        try {
            serverSocket = new ServerSocket(8081);
            while (true){
                server = new Server(serverSocket.accept());
            }
        }catch (IOException e){
         System.out.println("Server indisponivél");
        }finally {
            try {
             if(serverSocket != null)
                 serverSocket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    private static void initClient(){
        Client client = new Client("127.0.0.1",8081);
    }

    public static void main(String[]args){

        if(args.length !=0){
            initProgram(args);
        }else{
            System.out.println("Nenhum parâmetro foi digitado");
        }
    }
}
