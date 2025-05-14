package Data;

public class Representative {
    private int ID;
    private String name;
    public Representative(int ID, String name){
        setID(ID);
        setName(name);
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
