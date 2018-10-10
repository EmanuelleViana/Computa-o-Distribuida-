import java.io.*;
import java.net.*;

public class PrinterClass {

    private static void initProgram(String[]args){
       if(args.length != 0){
           if(args[0].equalsIgnoreCase("server")){
               initServer();
           }else if(args[0].equalsIgnoreCase("client")){

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




    public static void main(String[]args){

        if(args.length !=0){
            initProgram(args);
        }else{
            System.out.println("Nenhum parâmetro foi digitado");
        }
    }
}
