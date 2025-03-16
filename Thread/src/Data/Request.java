package Data;

import static java.lang.Thread.sleep;

public class Request extends  Inquiry{
    public  Request(){}
    @Override
    public String handling(){
        return ("Request:"+getCode());
    }
}
