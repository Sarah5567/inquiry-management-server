package Data;

public class Question extends  Inquiry{
    @Override
    public String handling(){
        return ("Question:"+getCode());
    }


    @Override
    public String getFolderName() {
        return "Question";
    }

    @Override
    public String getFileName() {
        return ""+code;
    }

    @Override
    public String getData() {
        return "code: "+code+",description: "+description+",documentNames:"+documentNames.toString()+",creationDate:"+creationDate;
    }
}
