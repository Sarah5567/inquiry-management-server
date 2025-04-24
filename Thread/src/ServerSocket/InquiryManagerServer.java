package ServerSocket;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class InquiryManagerServer {
    ServerSocket myServer;
    public InquiryManagerServer(){
        try {
            myServer=new ServerSocket(6000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void start(){
        try {
            Socket socket=myServer.accept();
            HandleClient handleClient=new HandleClient(socket);
            handleClient.start();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public  void stop(){
        try {
            myServer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
};
