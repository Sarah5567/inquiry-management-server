package Business;

import Data.Complaint;
import Data.Inquiry;
import Data.Question;
import Data.Request;
import HandleStoreFiles.HandleFiles;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class InquiryManager {
    private  BlockingQueue<InquiryHandling> queue = new LinkedBlockingQueue<>();
    private  Scanner scanner = new Scanner(System.in);
    private boolean isInquiryCreationActive = true;
    HandleFiles handleFiles=new HandleFiles();


    public void inquiryCreation() {
        Inquiry currentInquiry=null;
        System.out.println("chose number:" +
                "1:Question,  2:Request, 3:Complaint");
        String choose = scanner.nextLine();
        while (!choose.equals("exit")) {
            switch (choose) {
                case "1":
                    currentInquiry=new Question();
                    break;
                case "2":
                    currentInquiry=new Request();
                    break;
                case "3":
                    currentInquiry=new Complaint();
                    break;
                default:
                    System.out.println("error");
            }
            handleFiles.saveFile(currentInquiry);
            queue.add(new InquiryHandling(currentInquiry));

            System.out.println("chose number:" +
                    "1:Question,  2:Request, 3:Complaint");
            choose = scanner.nextLine();
        }

        isInquiryCreationActive = false;
        System.exit(0);
    }
    public void processInquiryManager() {
        while (isInquiryCreationActive) {
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
