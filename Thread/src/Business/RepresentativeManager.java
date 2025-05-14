package Business;

import Data.Representative;

import java.util.LinkedList;
import java.util.Queue;

public class RepresentativeManager {
    Queue<Representative> availableRepresentatives;
    private static RepresentativeManager instance;

    private RepresentativeManager(){
        availableRepresentatives = new LinkedList<>();
    }
    public static RepresentativeManager getInstance(){
        if (instance == null)
            instance = new RepresentativeManager();
        return instance;
    }
}
