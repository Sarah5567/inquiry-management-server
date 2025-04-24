package ClientServer;

import java.util.List;

public class Request {
    private InquiryManagerActions action;
    private List<Object> parameters;

    public Request(InquiryManagerActions action, List<Object>... parameters) {
        this.action = action;
        for(List<Object> p :parameters)
            this.parameters.add(p);
    }

    public InquiryManagerActions getAction() {
        return action;
    }

    public void setAction(InquiryManagerActions action) {
        this.action = action;
    }

    public List<Object> getParameters() {
        return parameters;
    }

    public void setParameters(List<Object> parameters) {
        this.parameters = parameters;
    }

}
