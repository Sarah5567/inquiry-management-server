package ServerSocket;
import Business.InquiryManager;
import ClientServer.RequestData;
import ClientServer.ResponseData;
import ClientServer.ResponseStatus;
import Data.Inquiry;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Queue;

public class HandleClient extends Thread{
    Socket clientSocket;
    InquiryManager inquiryManager=InquiryManager.getInstance();
    public HandleClient(Socket clientSocket){
        this.clientSocket=clientSocket;
    }
    public void handleClientRequest(){
        try {
            ObjectInputStream objectInputStream =new ObjectInputStream(clientSocket.getInputStream());
            Object obj=  objectInputStream.readObject();
            RequestData newRequest=(RequestData) obj;
            Queue<Inquiry> queue;
            ResponseData response;
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
                    inquiryManager.addInquiry((Inquiry) newRequest.getParameters().get(0));
                    break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void run() {
        handleClientRequest();
    }
}
