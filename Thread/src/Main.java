import Business.InquiryHandling;
import Business.InquiryManager;
import HandleStoreFiles.FileRename;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);

        }

//        InquiryHandling handling1 = new InquiryHandling();
//        Thread handling2 = new InquiryHandling();
//        InquiryHandling handling3 = new InquiryHandling();
//        InquiryHandling handling4 = new InquiryHandling();
//
//        handling1. createInquiry();
//        ((InquiryHandling) handling2).createInquiry();
//        handling3.createInquiry();
//        handling4.createInquiry();
//
//        handling1.start();
//        handling2.start();
//        handling3.start();
//        handling4.start();
//
//        handling1.run();
//        handling2.run();
//        handling3.run();
//        handling4.run();
//


//        Thread handling2 = new InquiryHandling();
//        InquiryHandling handling3 = new InquiryHandling();
//        InquiryHandling handling4 = new InquiryHandling();
//        InquiryHandling handling1 = new InquiryHandling();
//        InquiryHandling handling5 = new InquiryHandling();
//        Thread handling6 = new InquiryHandling();
//        InquiryHandling handling7 = new InquiryHandling();
//        InquiryHandling handling8 = new InquiryHandling();
//        InquiryHandling handling9 = new InquiryHandling();
//        Thread handling10 = new InquiryHandling();
//        InquiryHandling handling11 = new InquiryHandling();
//        InquiryHandling handling12 = new InquiryHandling();
//        InquiryHandling handling13 = new InquiryHandling();
//        Thread handling14 = new InquiryHandling();
//        InquiryHandling handling15 = new InquiryHandling();
//        InquiryHandling handling16 = new InquiryHandling();
//
//
//        handling1. createInquiry();
//        ((InquiryHandling) handling2).createInquiry();
//        handling3.createInquiry();
//        handling4.createInquiry();
//        handling5. createInquiry();
//        ((InquiryHandling) handling6).createInquiry();
//        handling7.createInquiry();
//        handling8.createInquiry();
//
//        handling9. createInquiry();
//        ((InquiryHandling) handling10).createInquiry();
//        handling11.createInquiry();
//        handling12.createInquiry();
//
//        handling13. createInquiry();
//        ((InquiryHandling) handling14).createInquiry();
//        handling15.createInquiry();
//        handling16.createInquiry();
//
//        handling1.start();
//        handling2.start();
//        handling3.start();
//        handling4.start();
//        handling5.start();
//        handling6.start();
//        handling7.start();
//        handling8.start();
//        handling9.start();
//        handling10.start();
//        handling11.start();
//        handling12.start();
//        handling13.start();
//        handling14.start();
//        handling15.start();
//        handling16.start();
//
//


        InquiryManager inquiryManager = new InquiryManager();
//        inquiryManager.inquiryCreation();
//        inquiryManager.processInquiryManager();


        Thread inquiryCreationThread = new Thread(() -> {
            try {
                inquiryManager.inquiryCreation();
            } catch (Exception e) {
                System.out.println("Error in inquiry creation: " + e.getMessage());
            }
        });

        Thread inquiryProcessingThread = new Thread(() -> {
            inquiryManager.processInquiryManager();
        });
        inquiryCreationThread.start();
        inquiryProcessingThread.start();
        try {
            inquiryCreationThread.join();
            inquiryProcessingThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }

        FileRename fileRename = new FileRename("Question", "new_");
        fileRename.start();

    }
}
