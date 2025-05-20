package Business;

import Data.*;
import HandleStoreFiles.*;

import java.io.File;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class InquiryManager {
    public static Integer nextCodeVal = 0;
    private static InquiryManager instance;
    private  Scanner scanner = new Scanner(System.in);
    private boolean isInquiryCreationActive = true;
    HandleFiles handleFiles=new HandleFiles();
    private static final BlockingQueue<Inquiry> queue ;
    private Map<Integer,Inquiry>inquiryHandlingMap=new HashMap<>();
    static {
        queue=new LinkedBlockingQueue<>();
        //loadInquiry();
    }
    private InquiryManager() {
        new MatchingThread().start();
    }

    public static InquiryManager getInstance(){
        if (instance==null)
            instance=new InquiryManager();
        return instance;
    }

    private static void loadInquiry(){
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
                    Inquiry inquiry=(Inquiry)newObj;
                    inquiry.setCode(Integer.valueOf(file.getName()));
                    queue.add(inquiry);

                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            }
        }
        nextCodeVal=max;
    }

    public InquiryStaus getInquiryStatus(int inquiryId){
        HandleFiles handleFiles=new HandleFiles();
        Inquiry inquiry = inquiryHandlingMap.get(inquiryId);
        InquiryStaus status=null;
        if(inquiry!=null)
            status =  inquiry.getStatus();
        File directory=new File("History");
        File[]directories=directory.listFiles();
        for(File f:directories){
            File[] files=f.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (Integer.parseInt(file.getName()) == inquiryId) {
                        inquiry = (Inquiry) handleFiles.readFile(file);
                        status = inquiry.getStatus();
                    }
                }
            }
        }

        return status;
    }
    public Representative ReturnRepresentativeByInquiryId(int inquiryId){
        HandleFiles handleFiles=new HandleFiles();

        Inquiry inquiry=inquiryHandlingMap.get(inquiryId);
        if(inquiry==null){
            File directory=new File("History");
            File[]directories=directory.listFiles();
            for(File f:directories){
                File[] files=f.listFiles();
                for(File file:files){
                    if(Integer.parseInt(file.getName())==inquiryId){
                        inquiry=(Inquiry)handleFiles.readFile(file);
                    }
                }
            }
        }
        if(inquiry==null)
            return null;
        Representative representative=null;
        File directory=new File("Representative");
        File[]representativeFiles=directory.listFiles();
        for(File f:representativeFiles){
            if(Integer.parseInt(f.getName())==inquiry.getRepresentativeID()) {
                representative = (Representative) handleFiles.readFile(f);
            }
        }
        return representative;
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
            currentInquiry.setCode(nextCodeVal++);
            queue.add(currentInquiry);

            System.out.println("chose number:" +
                    "1:Question,  2:Request, 3:Complaint");
            choose = scanner.nextLine();
        }

        isInquiryCreationActive = false;
        System.exit(0);
    }
//    public void processInquiryManager() {
//        while (isInquiryCreationActive) {
//            try {
//                InquiryHandling inquiryHandling = new InquiryHandling(queue.take());
//                if (inquiryHandling != null) {
//                    inquiryHandling.start();
//                }
//            } catch (InterruptedException e) {
//                System.out.println("Error processing inquiry: " + e.getMessage());
//                Thread.currentThread().interrupt();
//            }
//        }
//
//    }
    public Queue<Inquiry> allInquiry(){
        return queue;
    }
    public void addInquiry(Inquiry inquiry){
        inquiry.setCode(nextCodeVal++);
        handleFiles.saveFile(inquiry);
        System.out.println(inquiry.getData());
        inquiryHandlingMap.put(inquiry.getCode(),inquiry);
        queue.add(inquiry);
    }
    public void closeInquiry(Inquiry inquiry, Representative representative){
        new HandleFiles().moveInquiryToHistory(inquiry);
        inquiryHandlingMap.remove(inquiry.getCode());
        RepresentativeManager.getInstance().getAvailableRepresentatives().add(representative);
    }
}
