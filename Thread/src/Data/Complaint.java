package Data;
import java.util.Scanner;
public class Complaint extends  Inquiry{
    Scanner scanner=new Scanner(System.in);
    private String assignedBranch;
    @Override
    public void fillDataByUser(){
        super.fillDataByUser();
        System.out.println("insert assignedBranch");
        String assignedBranch=scanner.nextLine();
        this.assignedBranch=assignedBranch;
    }
    @Override
    public String handling(){
        return ("Complaint:"+getCode());
    }

    @Override
    public String getFolderName() {return "Complaint";}

    @Override
    public String getFileName() {
        return ""+code;
    }

    @Override
    public String getData() {
        return "code: "+code+",description: "+description+",documentNames:"+documentNames.toString()+",creationDate:"+creationDate+"assignedBranch"+assignedBranch;
    }
}
