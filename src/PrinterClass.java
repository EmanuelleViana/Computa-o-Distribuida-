public class PrinterClass {

    private static void initProgram(String[]args){
       if(args.length != 0){
           if(args[0].equalsIgnoreCase("server")){
               initServer();
           }else if(args[0].equalsIgnoreCase("client")){
                Client client = new Client("127.0.0.1",8081);
                Node node = new Node();
                node.connectClient(client);
           }else{
               System.out.println("Nenhuma parâmetro válido foi digitado");
           }
       }
    }

    private static  void initServer(){
        Server server = new Server();
    }




    public static void main(String[]args){

        if(args.length !=0){
            initProgram(args);
        }else{
            System.out.println("Nenhum parâmetro foi digitado");
        }
    }
}
