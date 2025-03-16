package Data;

public class Question extends  Inquiry{
    private  String assignedBranch;

    public String getAssignedBranch() {
        return assignedBranch;
    }

    public void setAssignedBranch(String assignedBranch) {
        this.assignedBranch = assignedBranch;
    }
    @Override
    public String handling(){
        return ("Question:"+getCode());
    }


}
