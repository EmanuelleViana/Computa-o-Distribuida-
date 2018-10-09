import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client{
    private String host;
    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Client(String host, int port){
        this.host = host;
        this.port = port;
    }


}

