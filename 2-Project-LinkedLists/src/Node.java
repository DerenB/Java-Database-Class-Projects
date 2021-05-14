public class Node {
    //VARIABLES
    private String value;
    private int position;
    private Node next;
    private Node previous;

    //DEFAULT CONSTRUCTOR
    public Node(String v, int p) {
        value = v;
        position = p;
    }

    //TO-STRING METHOD
    public String toString() {
        return (value + " " + position);
    }

    //COMPARE-TO METHOD
    public int compareTo(Node otherNode) {
        return(value.compareTo(otherNode.value));
    }

    //GETTERS AND SETTERS
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

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }
}
//END OF CLASS
