package Business;

public class RepresentativeManager {
    private static RepresentativeManager instance;

    private RepresentativeManager(){}
    public static RepresentativeManager getInstance(){
        if (instance == null)
            instance = new RepresentativeManager();
        return instance;
    }
}
