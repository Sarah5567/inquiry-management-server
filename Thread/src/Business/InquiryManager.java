package Business;

import Data.Complaint;
import Data.Inquiry;
import Data.Question;
import Data.Request;
import HandleStoreFiles.HandleFiles;
import HandleStoreFiles.IForSaving;

import java.io.File;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class InquiryManager {
    public static Integer nextCodeVal = 0;
    private static InquiryManager instance;
    private  Scanner scanner = new Scanner(System.in);
    private boolean isInquiryCreationActive = true;
    HandleFiles handleFiles=new HandleFiles();
    private static final BlockingQueue<Inquiry> queue ;
    static {
        queue=new LinkedBlockingQueue<>();
        loadInqury();
    }
    private InquiryManager() {
    }

    public static InquiryManager getInstance(){
        if (instance==null)
            instance=new InquiryManager();
        return instance;
    }

    private static void loadInqury(){
        int max=nextCodeVal;
        String [] namesFolder = {"Question","Request","Complaint"};
        HandleFiles handleFiles=new HandleFiles();
        for(String folder : namesFolder){
            File directory = new File(folder);
            File [] files = directory.listFiles();
            if (files!=null){
            for(File file :files){
                try{
                    IForSaving newObj = handleFiles.readFile(file);
                    queue.add((Inquiry) newObj);
                    if(((Inquiry) newObj).getCode()>max)
                        max=((Inquiry) newObj).getCode();
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            }
        }
        nextCodeVal=max;
    }

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
            queue.add(currentInquiry);

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
                InquiryHandling inquiryHandling = new InquiryHandling(queue.take());
                if (inquiryHandling != null) {
                    inquiryHandling.start();
                }
            } catch (InterruptedException e) {
                System.out.println("Error processing inquiry: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }

    }
    public Queue<Inquiry> allInquiry(){
        return queue;
    }
    public void addInquiry(Inquiry inquiry){
        handleFiles.saveFile(inquiry);
        queue.add(inquiry);
    }
}
