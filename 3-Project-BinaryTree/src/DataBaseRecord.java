public class DataBaseRecord {
    //VARIABLES
    private String fName;
    private String lName;
    private String id;

    //MAIN CONSTRUCTOR
    public DataBaseRecord(String l, String f, String i) {
        fName = f;
        lName = l;
        id = i;
    }

    //GETTERS AND SETTERS
    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //TO-STRING METHOD
    public String toString() {
        return (fName + " " + lName + " " + id);
    }
}
//END OF CLASS
