public class Index {
    //VARIABLES
    private Node head;
    private Node tail;

    //ADD THE FIRST VALUE OF THE LIST
    public void addToFront(Node node) {
        if (head == null) {
            tail = node;
        } else {
            head.setPrevious(node);
            node.setNext(head);
        }
        head = node;
    }

    //ADD THE LAST VALUE OF THE LIST
    public void addToEnd(Node node) {
        if (tail == null) {
            head = node;
        } else {
            tail.setNext(node);
            node.setPrevious(tail);
        }
        tail = node;
    }

    //INSERT VALUES IN ALPHABETICAL ORDER BY ASCII
    public void insert(Node newVal) {
        Node temp = head.getNext();

        //Moves through the list to find what value should be next
        while (temp.getValue().compareTo(newVal.getValue()) <= 0) {
            temp = temp.getNext();
        }

        //Inserts the value based on the Next value found
        (temp.getPrevious()).setNext(newVal);
        newVal.setPrevious(temp.getPrevious());
        temp.setPrevious(newVal);
        newVal.setNext(temp);
    }

    //CHECK THE ID LIST FOR DUPLICATES
    public boolean duplicate(String key) {
        boolean duped = false;
        Node current = head;

        while (current != null) {
            if (key.compareTo(current.getValue()) == 0) {
                duped = true;
                break;
            }
            current = current.getNext();
        }
        return duped;
    }

    //METHOD TO LOOK UP VALUES IN THE LIST
    public int find(String key) {
        int output = -1;
        Node current = head;
        while (current != null) {
            if (key.compareTo(current.getValue()) == 0) {
                output = current.getPosition();
                break;
            }
            current = current.getNext();
        }
        return output;
    }

    //METHOD TO RETURN VALUES OF SPECIFIC INDEX VALUE
    public String retVal(int key) {
        String output = "";
        Node current = head;
        while (current != null) {
            if (current.getPosition() == key) {
                output = current.getValue();
                break;
            }
            current = current.getNext();
        }
        return output;
    }

    //METHOD TO RETURN INDEX VALUES
    public int retIndex(int key) {
        Node current = head;
        int start = 1;
        while (start != key) {
            current = current.getNext();
            start++;
        }
        return current.getPosition();
    }

    //METHOD TO DELETE A STUDENT
    public void delete(int delVal) {
        Node current = head;
        while (current != null) {
            if (current.getPosition() == delVal) {
                current.getPrevious().setNext(current.getNext());
                current.getNext().setPrevious(current.getPrevious());
                current.setPrevious(null);
                current.setNext(null);
                break;
            }
            current = current.getNext();
        }
    }

    //METHOD TO PRINT OUT ALL VALUES OF THE LIST
    public void printList() {
        Node current = head;
        System.out.println("Head -> ");
        while (current != null) {
            System.out.print(current);
            System.out.println(" <-> ");
            current = current.getNext();
        }
        System.out.println("null");
    }
}
//END OF CLASS
