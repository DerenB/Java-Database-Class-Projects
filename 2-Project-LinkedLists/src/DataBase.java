import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataBase {
    //VARIABLES
    private DataBaseRecord data[]; //Creates the unordered database array
    private int deletions; //Variable to track the number of deletions
    private int nextDBRecord; //Variables to track number of objects in the database
    Scanner keyb = new Scanner(System.in); //Scanner for user inputs
    FileInputStream fis = null; //Input to read the data file
    Index firstNode, lastNode, idNode; //Create the three indexes

    //MAIN CONSTRUCTOR, SET SIZE OF 100. READS IN DATASET TEXT.
    public DataBase() {
        //Initializes database size, database record, deletions
        data = new DataBaseRecord[100];
        nextDBRecord = 0;
        deletions = 0;

        //Initializes the three linked lists
        firstNode = new Index();
        lastNode = new Index();
        idNode = new Index();

        //ABeginning and End values of Linked Lists
        String sVal = "     ";
        String eVal = "}}}}}";

        //Add Beginning value to the three lists
        DataBaseRecord sPerson = new DataBaseRecord(sVal,sVal,sVal);
        data[nextDBRecord] = sPerson;
        firstNode.addToFront(new Node(sVal,nextDBRecord));
        lastNode.addToFront(new Node(sVal,nextDBRecord));
        idNode.addToFront(new Node(sVal,nextDBRecord));
        nextDBRecord++;

        //Add Ending value to the three lists
        DataBaseRecord ePerson = new DataBaseRecord(eVal,eVal,eVal);
        data[nextDBRecord] = ePerson;
        firstNode.addToEnd(new Node(eVal,nextDBRecord));
        lastNode.addToEnd(new Node(eVal,nextDBRecord));
        idNode.addToEnd(new Node(eVal,nextDBRecord));
        nextDBRecord++;

        //Try to connect to the file
        try {
            fis = new FileInputStream("/Users/deren/Library/Mobile Documents/com~apple~CloudDocs/" +
                    "iCloudProgramming/2021-Winter/311-Java/ProjectTwo2/DataSet.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error reading the file.");
            e.printStackTrace();
        }

        //Scanner for fileinputstream
        Scanner fHandle = new Scanner(fis);

        //Read in the data from the data set
        while(fHandle.hasNext()) {
            String lastName = fHandle.next().toUpperCase();
            String firstName = fHandle.next().toUpperCase();
            String id = fHandle.next();

            //Checks if a duplicate was found
            if(idNode.duplicate(id)) {
                System.out.println(firstName + " " + lastName + " has a duplicate ID. Not adding to database.");
            } else {
                addThem(firstName,lastName,id);
            }
        }
        System.out.println();
    }

    //MAIN CONSTRUCTOR, SET THE SIZE TO WHAT THE USER INPUTS. READS IN DATASET TEXT
    public DataBase(int sz) {
        //Initializes database size, database record, deletions
        data = new DataBaseRecord[sz];
        nextDBRecord = 0;
        deletions = 0;

        //Initializes the three linked lists
        firstNode = new Index();
        lastNode = new Index();
        idNode = new Index();

        //ABeginning and End values of Linked Lists
        String sVal = "     ";
        String eVal = "}}}}}";

        //Add Beginning value to the three lists
        DataBaseRecord sPerson = new DataBaseRecord(sVal,sVal,sVal);
        data[nextDBRecord] = sPerson;
        firstNode.addToFront(new Node(sVal,nextDBRecord));
        lastNode.addToFront(new Node(sVal,nextDBRecord));
        idNode.addToFront(new Node(sVal,nextDBRecord));
        nextDBRecord++;

        //Add Ending value to the three lists
        DataBaseRecord ePerson = new DataBaseRecord(eVal,eVal,eVal);
        data[nextDBRecord] = ePerson;
        firstNode.addToEnd(new Node(eVal,nextDBRecord));
        lastNode.addToEnd(new Node(eVal,nextDBRecord));
        idNode.addToEnd(new Node(eVal,nextDBRecord));
        nextDBRecord++;

        //Try to connect to the file
        try {
            fis = new FileInputStream("/Users/deren/Library/Mobile Documents/com~apple~CloudDocs/" +
                    "iCloudProgramming/2021-Winter/311-Java/ProjectTwo2/DataSet.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error reading the file.");
            e.printStackTrace();
        }

        //Scanner for fileinputstream
        Scanner fHandle = new Scanner(fis);

        //Read in the data from the data set
        while(fHandle.hasNext()) {
            String lastName = fHandle.next().toUpperCase();
            String firstName = fHandle.next().toUpperCase();
            String id = fHandle.next();

            //Checks if a duplicate was found
            if(idNode.duplicate(id)) {
                System.out.println(firstName + " " + lastName + " has a duplicate ID. Not adding to database.");
            } else {
                addThem(firstName,lastName,id);
            }
        }
        System.out.println();
    }

    //METHOD FOR PROMPTING USER TO ADD A STUDENT
    public void addIt() {
        //Variables
        String firstName, lastName, id;

        //Get the info from the user
        System.out.println("Enter the new Student's information (First, Last, ID): ");
        firstName = keyb.next().toUpperCase();
        lastName = keyb.next().toUpperCase();
        id = keyb.next();

        //Check ID length
        if(idenLength(id)) return;

        //Check if entry is a Duplicate
        if(idNode.duplicate(id)) {
            System.out.println("That Student ID is already in the system. Try again."+"\n");
        } else {
            addThem(firstName,lastName,id);
        }
    }

    //METHOD TO ADD STUDENT TO DATABASE AND TO THE LISTS
    public void addThem(String fin, String lin, String iin) {
        //Creates the student object and adds it to the database
        DataBaseRecord newPerson = new DataBaseRecord(fin,lin,iin);
        data[nextDBRecord] = newPerson;

        //Creates the IndexRecord and inserts them into the List
        firstNode.insert(new Node(fin,nextDBRecord));
        lastNode.insert(new Node(lin,nextDBRecord));
        idNode.insert(new Node(iin,nextDBRecord));

        //Increments the database count
        nextDBRecord++;
    }

    //METHOD TO DELETE A STUDENT BY ID
    public void deleteIt() {
        System.out.println("Enter the ID of the student: ");
        String key = keyb.next();

        //Checks if ID is a valid length
        if(idenLength(key)) return;

        //Search List for the ID
        int queryIndex = idNode.find(key);

        //Checks if the ID exists
        if (queryIndex == -1) {
            System.out.println("ID nto found. Try again.");
        } else {
            //Values for the student to be deleted
            String fName = firstNode.retVal(queryIndex);
            String lName = lastNode.retVal(queryIndex);
            String id = idNode.retVal(queryIndex);

            //Asks if the user wants to delete the user
            System.out.println("Delete Student: " + toString(fName,lName,id) + " ? Y/N");
            String response = keyb.next().toLowerCase();
            if (response.equals("y")) {
                firstNode.delete(queryIndex);
                lastNode.delete(queryIndex);
                idNode.delete(queryIndex);
                System.out.println("Student Deleted."+"\n");
            } else {
                System.out.println("Not Deleting."+"\n");
            }
        }
        //Increments the number of deletions count
        deletions++;
    }

    //METHOD TO FIND A STUDENT IN THE DATABASE BY ID
    public void findIt() {
        System.out.println("Enter the ID of the student: ");
        String key = keyb.next();

        //Checks the length of the ID input
        if(idenLength(key)) return;

        //Checks the List for that ID
        int queryIndex = idNode.find(key);
        if (queryIndex == -1) {
            System.out.println("ID not found. Try again."+"\n");
        } else {
            //Finds the first/last name and outputs them to the user
            String fName = firstNode.retVal(queryIndex);
            String lName = lastNode.retVal(queryIndex);
            String id = idNode.retVal(queryIndex);
            System.out.println("Found: "+toString(fName,lName,id) + "\n");
        }
    }

    //PRINT ID ASCENDING
    public void ListByIDAscending() {
        System.out.println("ID Ascending:");
        print("up","id");
    }

    //PRINT FIRST NAME ASCENDING
    public void ListByFirstAscending() {
        System.out.println("First Name Ascending:");
        print("up","first");
    }

    //PRINT LAST NAME ASCENDING
    public void ListByLastAscending() {
        System.out.println("Last Name Ascending:");
        print("up","last");
    }

    //PRINT ID DESCENDING
    public void ListByIDDescending() {
        System.out.println("ID Descending:");
        print("down","id");
    }

    //PRINT FIRST NAME DESCENDING
    public void ListByFirstDescending() {
        System.out.println("First Name Descending:");
        print("down","first");
    }

    //PRINT LAST NAME DESCENDING
    public void ListByLastDescending() {
        System.out.println("Last Name Descending:");
        print("down","last");
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

    //TO STRING METHOD
    public String toString(String fname, String lname, String id) {
        return (lname + " " + fname + " " + id);
    }

    //METHOD FOR SELECTING WHAT TO PRINT
    public void print(String direction, String type) {
        if(direction.equals("up")) {
            if(type.equals("first")) {
                uPrint(firstNode,1);
            } else if (type.equals("last")) {
                uPrint(lastNode,1);
            } else if (type.equals("id")) {
                uPrint(idNode,1);
            } else {
                System.out.println("Error.");
            }
        } else if (direction.equals("down")) {
            if(type.equals("first")) {
                uPrint(firstNode,0);
            } else if (type.equals("last")) {
                uPrint(lastNode,0);
            } else {
                uPrint(idNode,0);
            }
        } else {
            System.out.println("Error.");
        }
        System.out.println();
    }

    //METHOD TO LOOP THROUGH DATABASE
    public void uPrint(Index in,int a) {
        int i,spot;
        int limit = nextDBRecord - deletions;

        //ASCENDING OUTPUT
        if(a == 1) {
            for(i = 2; i < limit; i++) {
                spot = in.retIndex(i);
                System.out.println(data[spot]);
            }
        } else {    //DESCENDING OUTPUT
            for(i = limit-1; i > 1; i--) {
                spot = in.retIndex(i);
                System.out.println(data[spot]);
            }
        }
    }

    //METHOD TO PRINT OUT THE DOUBLY LINKED LISTS
    public void printMe() {
        firstNode.printList();
        lastNode.printList();
        idNode.printList();
    }
}
//END OF CLASS


