package ServerSocket;
import Business.InquiryManager;
import ClientServer.*;
import Data.Inquiry;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Queue;

public class HandleClient extends Thread{
    Socket clientSocket;
    InquiryManager inquiryManager=InquiryManager.getInstance();
    public HandleClient(Socket clientSocket){
        this.clientSocket=clientSocket;
    }
    ObjectInputStream objectInputStream=null;
    ObjectOutputStream objectOutputStream=null;
    public void handleClientRequest(){
        try {
            objectInputStream=new ObjectInputStream(clientSocket.getInputStream());
//            Object obj=
            RequestData newRequest=(RequestData) objectInputStream.readObject();
            System.out.println("after recive");
            Queue<Inquiry> queue;
            ResponseData response=null;
            switch (newRequest.getAction().toString())
            {
                case "ALL_INQUIRY":{
                    try {
                        queue = inquiryManager.allInquiry();
                        response=new ResponseData(ResponseStatus.SCCESS,"allInquiry",queue)  ;
                    }
                    catch (Exception e) {
                        response=new ResponseData(ResponseStatus.FAIL,e.getMessage(),"fail") ;
                    }
                }
                    break;
                case "ADD_INQUIRY":
                    inquiryManager.addInquiry((Inquiry) newRequest.getParameters()[0]);
                    // response
                    break;
                case "TEST":
                    response=new ResponseData(ResponseStatus.SCCESS,"test","test");

            }

            objectOutputStream=new ObjectOutputStream(clientSocket.getOutputStream());
            objectOutputStream.writeObject(response);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                objectInputStream.close();
                objectOutputStream.close();
                clientSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void run() {
        handleClientRequest();
    }
}
