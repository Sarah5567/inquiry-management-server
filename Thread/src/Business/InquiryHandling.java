package Business;
import Data.Complaint;
import Data.Inquiry;
import Data.Question;
import Data.Request;
import java.util.Random;
import java.util.Scanner;


public class InquiryHandling extends Thread {
    private Inquiry currentInquiry;
    Scanner scanner = new Scanner(System.in);
    Random rand = new Random();
    public InquiryHandling(Inquiry inquiry) {
        this.currentInquiry=inquiry;
    }
    public  InquiryHandling()
    {
    }

    @Override
    public void run() {
        String nameClass = currentInquiry.getClass().getName();
        int estimationTime;
        switch (nameClass){
            case "Data.Question": estimationTime=rand.nextInt(5) + 1;
                break;
            case "Data.Request": estimationTime=rand.nextInt(6) + 10;
                break;
            case "Data.Complaint": estimationTime=rand.nextInt(21) + 20;
                break;
            default:
               throw new Error("erorr");
        }
        System.out.println(currentInquiry.handling()+"  estimationTime: "+estimationTime);
        try {
            if(estimationTime>5&&activeCount()>10)
                Thread.yield();
            else {
                sleep(estimationTime*1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        /// קשור לסעיף ב ו- ג
 //      try {
//            if(currentThread().getClass().getName().equals(("Request")))
//               sleep(3000);
//            else
//                sleep(5000);
//            if(currentThread().getClass().getName().equals("Question"))
//            {
//                currentThread().setPriority(MAX_PRIORITY);
//            }
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
    @Deprecated
    public void createInquiry() throws Exception {
        System.out.println("chose number:" +
                "1:Question,  2:Request, 3:Complaint");
        int num= scanner.nextInt();
        switch (num){
            case 1:currentInquiry=new Question();
            break;
            case 2:currentInquiry=new Request();
            break;
            case 3:currentInquiry=new Complaint();
            break;
            default:throw new Exception("error");
        }
    }
}
