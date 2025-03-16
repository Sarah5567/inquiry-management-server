package Business;

import Data.Complaint;
import Data.Question;
import Data.Request;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class InquiryManager {
    private  BlockingQueue<InquiryHandling> queue = new LinkedBlockingQueue<>();
    private  Scanner scanner = new Scanner(System.in);
    private boolean isInquiryCreationActive = true;


    public void inquiryCreation() {
        System.out.println("chose number:" +
                "1:Question,  2:Request, 3:Complaint");
        String choose = scanner.nextLine();
        while (!choose.equals("exit")) {
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
                    System.out.println("error");
            }
            System.out.println("chose number:" +
                    "1:Question,  2:Request, 3:Complaint");
            choose = scanner.nextLine();
        }

        isInquiryCreationActive = false;
        System.exit(0);
    }
    public void processInquiryManager() {
        while (isInquiryCreationActive || !queue.isEmpty()) {
            try {
                InquiryHandling inquiryHandling = queue.take();
                if (inquiryHandling != null) {
                    inquiryHandling.start();
                }
            } catch (InterruptedException e) {
                System.out.println("Error processing inquiry: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}
