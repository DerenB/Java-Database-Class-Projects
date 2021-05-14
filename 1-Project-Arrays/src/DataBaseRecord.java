public class DataBaseRecord {
    private String fName;
    private String lName;
    private String id;

    public DataBaseRecord(String f, String l, String i) {
        fName = f;
        lName = l;
        id = i;
    }

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

    public String toString() {
        return (fName + " " + lName + " " + id);
    }
}
