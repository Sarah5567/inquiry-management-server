package Data;

public class Complaint extends  Inquiry{
    public String assignedBranch;
    @Override
    public String handling(){
        return ("Complaint:"+getCode());
    }
}
