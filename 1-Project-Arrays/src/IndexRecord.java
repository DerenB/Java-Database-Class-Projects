public class IndexRecord {
    private String value;
    private int position;

    public IndexRecord(String f, int p) {
        value = new String(f);
        position = p;
    }

    public IndexRecord(IndexRecord otherIndex) {
        value = new String(otherIndex.value);
        position = otherIndex.position;
    }

    public String toString() {
        return (value + " " + position);
    }


    public int compareTo(IndexRecord otherIndex) {
        //Orders alphabetically by last name
        return(value.compareTo(otherIndex.value));
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
