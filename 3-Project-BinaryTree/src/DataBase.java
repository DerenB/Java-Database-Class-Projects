import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataBase {
    private DataBaseRecord data[]; //Creates the unordered database array
    private int deletions;  //Variable to track the number of deletions
    private int nextDBRecord;   //Variable to track number of objects in the database
    Scanner keyb = new Scanner(System.in);  //Scanner for user inputs
    FileInputStream fis = null; //Input stream to read the data file
    Tree lastTree, firstTree, idTree;  //Create the three trees

    //MAIN CONSTRUCTOR, SET SIZE OF 125. READS IN DATASET TEXT.
    public DataBase() {
        //Initializes the array size, database record, deletions
        data = new DataBaseRecord[125];
        nextDBRecord = 0;
        deletions = 0;

        //Initializes the three Trees
        lastTree = new Tree();
        firstTree = new Tree();
        idTree = new Tree();

        //Try to connect to the data file
        try {
            fis = new FileInputStream("/Users/deren/Library/Mobile Documents/com~apple~CloudDocs/" +
                    "iCloudProgramming/2021-Winter/311-Java/ProjectThree3/DataSet.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error reading the file.");
            e.printStackTrace();
        }

        //Scanner for file input stream
        Scanner fHandle = new Scanner(fis);

        //Create the Root of the Trees
        String lName = "MMMMMMMMMMMMMMMMMMMM";
        String fName = "MMMMMMMMMMMMMMMMMMMM";
        String idd = "55.55";
        DataBaseRecord baseStudent = new DataBaseRecord(lName,fName,idd);
        data[nextDBRecord] = baseStudent;
        nextDBRecord++;
        addThem(lName,fName,idd);

        //Read in the data from the data file
        while(fHandle.hasNext()) {
            //Assigns the three values to strings
            String lastName = fHandle.next().toUpperCase();
            String firstName = fHandle.next().toUpperCase();
            String id = fHandle.next();

            //Checks if a duplicate was found
            if (idTree.duplicate(id)) {
                System.out.println(lastName + " " + firstName + " has a duplicate ID. Not adding to database.");
            } else {
                addThem(lastName,firstName,id);
            }
        }
    }

    //MAIN CONSTRUCTOR, CUSTOM SET SIZE. READS IN DATASET TEXT.
    public DataBase(int sz) {
        //Initializes the array size, database record, deletions
        data = new DataBaseRecord[sz];
        nextDBRecord = 0;
        deletions = 0;

        //Initializes the three Trees
        lastTree = new Tree();
        firstTree = new Tree();
        idTree = new Tree();

        //Try to connect to the data file
        try {
            fis = new FileInputStream("/Users/deren/Library/Mobile Documents/com~apple~CloudDocs/" +
                    "iCloudProgramming/2021-Winter/311-Java/ProjectThree3/DataSet.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error reading the file.");
            e.printStackTrace();
        }

        //Scanner for file input stream
        Scanner fHandle = new Scanner(fis);

        //Create the Root of the Trees
        String lName = "MMMMMMMMMMMMMMMMMMMM";
        String fName = "MMMMMMMMMMMMMMMMMMMM";
        String idd = "55.55";
        DataBaseRecord baseStudent = new DataBaseRecord(lName,fName,idd);
        data[nextDBRecord] = baseStudent;
        nextDBRecord++;
        addThem(lName,fName,idd);

        //Read in the data from the data file
        while(fHandle.hasNext()) {
            //Assigns the three values to strings
            String lastName = fHandle.next().toUpperCase();
            String firstName = fHandle.next().toUpperCase();
            String id = fHandle.next();

            //Checks if a duplicate was found
            if (idTree.duplicate(id)) {
                System.out.println(lastName + " " + firstName + " has a duplicate ID. Not adding to database.");
            } else {
                addThem(lastName,firstName,id);
            }
        }
    }

    //PROMPTS THE USER TO ADD A STUDENT
    public void addIt() {
        //Variables
        String lastName, firstName, id;

        //Gets the new values from the user
        System.out.println("Enter the new Student's information (Last, First, ID): ");
        lastName = keyb.next().toUpperCase();
        firstName = keyb.next().toUpperCase();
        id = keyb.next().toUpperCase();

        //Checks if the ID is the right length
        if(idenLength(id)) return;

        //Checks if the ID is a duplicate
        if(idTree.duplicate(id)) {
            System.out.println("That Student ID is already in the system. Try again."+"\n");
        } else {
            addThem(lastName,firstName,id);
            System.out.println("Student added."+"\n");
        }
    }

    //METHOD TO ADD STUDENT TO DATABASE AND TO THE TREES
    public void addThem(String lin, String fin, String iin) {
        //Creates the student object and adds it to the database
        DataBaseRecord newStudent = new DataBaseRecord(lin,fin,iin);
        data[nextDBRecord] = newStudent;

        //Inserts them into the tree
        lastTree.inserto(new Node(lin,nextDBRecord,true,true));
        firstTree.inserto(new Node(fin,nextDBRecord,true,true));
        idTree.inserto(new Node(iin,nextDBRecord,true,true));
        nextDBRecord++;
    }

    //DELETION METHOD
    public void deleteIt() {
        //Prompts the user for the ID of the deletion
        System.out.println("Enter the ID of the student for deletion: ");
        String key = keyb.next();

        //Checks if ID is a valid length
        if(idenLength(key)) return;

        //Search if the ID exists
        int queryTree = idTree.find(key);

        //Result of searching the tree
        if (queryTree == -1) {
            System.out.println("ID not found. Try again."+"\n");
        } else {
            //Values of student for deletion
            String lName = data[queryTree].getlName();
            String fName = data[queryTree].getfName();
            String id = data[queryTree].getId();

            //Prompt if user wants to delete
            System.out.println("Found student: " + data[queryTree]);
            System.out.println("Proceed? Y/N");
            String response = keyb.next().toLowerCase();
            if(response.equals("y")) {
                lastTree.dede(lName);
                firstTree.dede(fName);
                idTree.dede(id);
                deletions++;
                System.out.println("Student Deleted."+"\n");
            } else {
                System.out.println("Not Deleting."+"\n");
            }
        }
    }

    //METHOD TO FIND A STUDENT IN THE DATABASE BY ID
    public void findIt() {
        //Prompts the user for the ID
        System.out.println("Enter the ID of the student to find: ");
        String key = keyb.next();

        //Checks if the ID is the right length
        if(idenLength(key)) return;

        //Gets the database record value of the found student
        int queryIndex = idTree.find(key);
        if (queryIndex == -1) {
            System.out.println("ID not found. Try again.");
        } else {
            System.out.println("Found: " + data[queryIndex]);
        }
        System.out.println();
    }

    //METHOD TO CHECK ID LENGTH
    public boolean idenLength(String key) {
        boolean output = false;
        int leng = key.length();
        String out="";
        if(leng != 5) {
            if (leng > 5) out = "long";
            if (leng < 5) out = "short";
            System.out.println("User ID is too " + out + ", needs to be 5 characters. Try again."+"\n");
            output = true;
        }
        return output;
    }

    //METHOD FOR TO-STRING
    public String toString(String lName, String fName, String id) {
        return (lName + " " + fName + " " + id);
    }

    //METHOD TO LIST STUDENTS BY ID IN ASCENDING ORDER
    public void ListByIDAscending() {
        System.out.println("ID Ascending:"+"\n");
        uPrint(idTree);
        System.out.println();
    }

    //METHOD TO LIST STUDENTS BY FIRST NAME IN ASCENDING ORDER
    public void ListByFirstAscending() {
        System.out.println("First Name Ascending"+"\n");
        uPrint(firstTree);
        System.out.println();
    }

    //METHOD TO LIST STUDENTS BY LAST NAME IN ASCENDING ORDER
    public void ListByLastAscending() {
        System.out.println("Last Name Ascending"+"\n");
        uPrint(lastTree);
        System.out.println();
    }

    //METHOD TO LIST STUDENTS BY ID IN DESCENDING ORDER
    public void ListByIDDescending() {
        System.out.println("ID Descending:"+"\n");
        dPrint(idTree);
        System.out.println();
    }

    //METHOD TO LIST STUDENTS BY FIRST NAME IN DESCENDING ORDER
    public void ListByFirstDescending() {
        System.out.println("First Name Descending:"+"\n");
        dPrint(firstTree);
        System.out.println();
    }

    //METHOD TO LIST STUDENTS BY LAST NAME IN DESCENDING ORDER
    public void ListByLastDescending() {
        System.out.println("Last Name Descending:"+"\n");
        dPrint(lastTree);
        System.out.println();
    }

    //METHOD TO PRINT BY SPECIFIC TREE ORDER ASCENDING
    public void uPrint(Tree inTree) {
        //Gets the lowest value of the tree
        Node rover = inTree.miniMe();
        //Gets the position of the lowest value
        int query = rover.getPosition();
        System.out.println(data[query]);
        while(true) {
            //Checks if it's the last value of the tree
            if((rover.isRightThread() && rover.getSuccessor() == null)) {
                break;
            } else if (rover.isRightThread()) {
                rover = rover.getSuccessor();
                query = rover.getPosition();
                //Skips the default root value
                if(data[query].getfName().equals("MMMMMMMMMMMMMMMMMMMM")) continue;
                System.out.println(data[query]);
            } else {
                rover = inTree.minVal(rover.getRightChild());
                query = rover.getPosition();
                //Skips the default root value
                if(data[query].getfName().equals("MMMMMMMMMMMMMMMMMMMM")) continue;
                System.out.println(data[query]);
            }
        }
    }

    //METHOD TO PRINT BY SPECIFIC TREE ORDER DESCENDING
    public void dPrint(Tree inTree) {
        //Gets the highest value of the tree
        Node rover = inTree.maxMe();
        //Gets the position of the highest value
        int query = rover.getPosition();
        System.out.println(data[query]);
        while(true) {
            //Checks if it's the first value of the tree
            if((rover.isLeftThread()) && rover.getPredecessor() == null) {
                break;
            } else if (rover.isLeftThread()) {
                rover = rover.getPredecessor();
                query = rover.getPosition();
                //Skips the default root value
                if(data[query].getfName().equals("MMMMMMMMMMMMMMMMMMMM")) continue;
                System.out.println(data[query]);
            } else {
//                rover = inTree.maxVal(rover.getLeftChild());
                if ((rover = inTree.maxVal(rover.getLeftChild()))==null) {
                    break;
                } else {
                    query = rover.getPosition();
                    //Skips the default root value
                    if(data[query].getfName().equals("MMMMMMMMMMMMMMMMMMMM")) continue;
                    System.out.println(data[query]);
                }
            }
        }
    }

}
//END OF CLASS