package ServerSocket;

import Data.Inquiry;
import Data.Request;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Queue;

public class HandleClient extends Thread{
    Socket clientSocket;
    public HandleClient(Socket clientSocket){
        this.clientSocket=clientSocket;
    }
    public void handleClientRequest(){
        try {
            ObjectInputStream objectInputStream =new ObjectInputStream(clientSocket.getInputStream());
            Request obj= (Request) objectInputStream.readObject();
            Queue<Inquiry> queue;
            switch ()
            {
                case "ALL_INQUIRY":queue=allInquiry();
                break;
                case "ADD_INQUIRY":addInquiry(obj.)

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
