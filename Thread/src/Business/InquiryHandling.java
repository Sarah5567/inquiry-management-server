package Business;
import Data.Complaint;
import Data.Inquiry;
import Data.Question;
import Data.Request;
import java.util.Random;
import java.util.Scanner;
public class InquiryHandling extends Thread  {
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
        String nameClass = currentInquiry.getClass().getSimpleName();
        int estimationTime;

        switch (nameClass){
            case "Question":{ estimationTime=rand.nextInt(5) + 1;
                currentThread().setPriority(MAX_PRIORITY);
            }
            break;
            case "Request": estimationTime=rand.nextInt(6) + 10;
                break;
            case "Complaint": estimationTime=rand.nextInt(21) + 20;
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
                System.out.println("finish");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
