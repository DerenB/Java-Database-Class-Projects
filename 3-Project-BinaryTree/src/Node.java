public class Node {
    //VARIABLES
    private String value;
    private int position;
    private Node leftChild;
    private Node rightChild;
    private Node parent;
    private Node predecessor;
    private Node successor;
    boolean rightThread;
    boolean leftThread;

    //DEFAULT CONSTRUCTOR
    public Node(String v, int p, boolean rthread, boolean lthred) {
        value = v;
        position = p;
        rightThread = rthread;
        leftThread = lthred;
    }

    //TO STRING METHOD
    public String toString() {
        return (value + " " + position);
    }

    //COMPARE TO METHOD
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

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public boolean isRightThread() {
        return rightThread;
    }

    public void setRightThread(boolean rightThread) {
        this.rightThread = rightThread;
    }

    public boolean isLeftThread() {
        return leftThread;
    }

    public void setLeftThread(boolean leftThread) {
        this.leftThread = leftThread;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Node predecessor) {
        this.predecessor = predecessor;
    }

    public Node getSuccessor() {
        return successor;
    }

    public void setSuccessor(Node successor) {
        this.successor = successor;
    }
}
//END OF CLASS
