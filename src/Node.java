import java.io.*;
import java.net.*;
import java.util.*;

public class Node {
    private long osn = 0;
    private long hsn = 0;
    private boolean wait;
    private boolean using;
    private static final int port = 8081;
    private static String defaultIp = "127.0.0.1";
    private String ipHost;
    private ServerSocket server;
    private HashMap<String, Message> messageList;
    private boolean use,waiting;

    public Node(String defaultIp){
        this.defaultIp = defaultIp;
        osn = 0;
        hsn = System.currentTimeMillis();
        messageList = new HashMap<>();
        new Thread(this::initClient).start();
    }

    private void sendMessage(String id, String message){
        Socket socket = null;
        try {
            socket = new Socket(id,port);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            printWriter.println(message);
            printWriter.close();
            socket.close();
        }catch (UnknownHostException e){
            e.printStackTrace();
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    private void sendAnswer(Message message){
        sendMessage("ok",message.getHost());
    }

    private boolean grantAcessCriticalZone(){
        wait = true;
        osn = hsn + 1;
        messageList.values().stream().filter(message -> (!message.isAccess())).forEach(message -> sendRequest(osn,message.getHost()));
        wait = false;
        use = false;
        return true;
    }

    private void sendRequest(long osn, String ip){
        System.out.println("OSN: "+osn+" ,Ip: "+defaultIp);
        sendMessage(osn+", IP: "+this.defaultIp,ip);
    }

    private void cancel(){
        try{
            server.close();
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    private void addMessage(Message message){
        if(!messageList.containsKey(message.getHost()))
            messageList.put(message.getHost(),message);
    }

    private void acessCriticalZone(int i,Message message){
        boolean priority;
        hsn = Math.max(hsn,i);
        priority = (i > osn)||((i == osn));

        if(use || (wait && priority)){
            message.setAccess(true);
        }else if(!(use|| wait) || (wait && (! message.isAccess()) && (!priority))){
            if(message != null){
                message.setAccess(false);
                sendAnswer(this.defaultIp,message.getHost());
            }
        }else if(wait && message.isAccess() && (!priority)){
            message.setAccess(false);
            sendAnswer(this.defaultIp,message.getHost());
            sendRequest(osn,defaultIp);
        }

    }

    private void response(Message message){
        message.setAccess(true);
    }

    private void release(){
        use = false;
        messageList.values().stream().filter(Message::isReply).forEach(this::sendAnswer);
    }

    private void initClient(){
        try{
        server = new ServerSocket(port);
        while (true){
            if(server.isClosed()){
                break;
            }
            Socket connection = server.accept();

        }
        }catch (Exception ex){

        }
    }
}
