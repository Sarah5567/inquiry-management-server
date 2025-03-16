package Business;

import Data.Complaint;
import Data.Question;
import Data.Request;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class InquiryManager  {
    private Queue<InquiryHandling> queue;
    Scanner scanner=new Scanner(System.in);
    public  InquiryManager(){
        queue= new LinkedList<>();;
    }
    public  void inquiryCreation() throws Exception
    {
        System.out.println("chose number:" +
                "1:Question,  2:Request, 3:Complaint");
        String choose= scanner.nextLine();
        while(!choose.equals("exit")) {
            switch (choose) {
                case "1":
                    queue.add(new InquiryHandling(new Question()));
                    break;
                case "2":
                    queue.add(new InquiryHandling(new Request()));
                    break;
                case "3":
                    queue.add(new InquiryHandling(new Complaint()));
                    break;
                default:
                    throw new Exception("error");
            }
            System.out.println("chose number:" +
                    "1:Question,  2:Request, 3:Complaint");
            choose = scanner.nextLine();
        }
    }
    public void processInquiryManager() {
        while (!queue.isEmpty()) {
            InquiryHandling inquiryHandling = queue.remove();
            if (inquiryHandling != null) {
                try {
                    inquiryHandling.start();
                } catch (Exception e) {
                    System.out.println("Error processing inquiry: " + e.getMessage());
                }
            }
        }
    }

}
