package ServerSocket;

public class mainServer
{
    public static void main(String[] args) {
       InquiryManagerServer inquiryManagerServer=new InquiryManagerServer();
       inquiryManagerServer.start();
       inquiryManagerServer.stop();
    }
}
