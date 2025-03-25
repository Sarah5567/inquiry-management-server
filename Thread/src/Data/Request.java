package Data;

import static java.lang.Thread.sleep;

public class Request extends  Inquiry{
    public  Request(){}
    @Override
    public String handling(){
        return ("Request:"+getCode());
    }

    @Override
    public String getFolderName() {
        return "Request";
    }

    @Override
    public String getFileName() {
        return ""+code;
    }

    @Override
    public String getData() {
        return className+","+"code: "+code+",description: "+description+",documentNames:"+documentNames.toString()+",creationDate:"+creationDate;
    }
}
