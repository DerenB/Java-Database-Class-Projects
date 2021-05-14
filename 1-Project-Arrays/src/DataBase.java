import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataBase {
    private DataBaseRecord data[];
    private int nextDBRecord;
    Index firstIndex, lastIndex, idIndex;
    Scanner keyb = new Scanner(System.in);
    FileInputStream fis = null;

    //Main Constructor, set size of 100. Reads in dataset text.
    public DataBase() {
        data = new DataBaseRecord[100];
        nextDBRecord = 0;
        firstIndex = new Index();
        lastIndex = new Index();
        idIndex = new Index();

        //Try to connect to the file
        try {
            fis = new FileInputStream("/Users/deren/Library/Mobile Documents/com~apple~CloudDocs/" +
                    "iCloudProgramming/2021-Winter/311-Java/ProjectOne1/DataSet.txt");
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
            if (duplicate(id)) {
                System.out.println(firstName + " " + lastName + " has a duplicate ID. Not adding to database.");
            } else {
                addThem(firstName,lastName,id);
            }
        }
        System.out.println();
    }

    //Main Constructor, custom set size. Reads in dataset txt.
    public DataBase(int sz) {
        data = new DataBaseRecord[100];
        nextDBRecord = 0;
        firstIndex = new Index();
        lastIndex = new Index();
        idIndex = new Index();

        //Try to connect to the file
        try {
            fis = new FileInputStream("/Users/deren/Library/Mobile Documents/com~apple~CloudDocs/" +
                    "iCloudProgramming/2021-Winter/311-Java/ProjectOne1/DataSet.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error reading the file.");
            e.printStackTrace();
        }

        //Scanner for fileinputstream
        Scanner fHandle = new Scanner(fis);

        //Read in the data from the data set
        while(fHandle.hasNext()) {
            String firstName = fHandle.next().toUpperCase();
            String lastName = fHandle.next().toUpperCase();
            String id = fHandle.next();

            //Checks if a duplicate was found
            if (duplicate(id)) {
                System.out.println(firstName + " " + lastName + " has a duplicate ID. Not adding to database.");
            } else {
                addThem(firstName,lastName,id);
            }
        }
        System.out.println();
    }

    //Add a new Person to the database
    public void addIt() {
        //Variables
        String firstName, lastName, id;

        //Get the info from the user
        System.out.println("Enter the new Person's information (First, Last, ID): ");
        firstName = keyb.next().toUpperCase();
        lastName = keyb.next().toUpperCase();
        id = keyb.next();

        //Check ID length
        if(idenLength(id)) {
            return;
        }

        //Checks if duplicate was found
        if (duplicate(id)) {
            System.out.println("That user's ID is already in the system. Try again.");
        } else {
            addThem(firstName,lastName,id);
        }
    }

    //Method to Add Students to database
    public void addThem(String fin, String lin, String iin) {
        DataBaseRecord newPerson = new DataBaseRecord(fin,lin,iin);
        data[nextDBRecord] = newPerson;

        //Creates the IndexRecord and inserts them into index
        firstIndex.insert(new IndexRecord(fin,nextDBRecord),nextDBRecord);
        lastIndex.insert(new IndexRecord(lin,nextDBRecord),nextDBRecord);
        idIndex.insert(new IndexRecord(iin,nextDBRecord),nextDBRecord);
        nextDBRecord++;
    }

    //Method to check for Duplicate ID
    public boolean duplicate(String key) {
        boolean duped = false;
        for (int i = nextDBRecord; i > 0; i--) {
            if ((key.compareTo(data[i-1].getId())) == 0) {
                duped = true;
                break;
            }
        }
        return duped;
    }

//    public boolean duplicate(String key) {
//        return (idIndex.duplicate(key));
//    }

    //Method to check ID length
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

    //Method to Find a Student by Id
    public void findIt() {
        System.out.println("Enter the ID of the student: ");
        String key = keyb.next();
        if (idenLength(key)) {
            return;
        }
        int queryIndex = idIndex.find(key);
        if (queryIndex == -1) {
            System.out.println("ID not found. Try again.");
        } else {
            String firstName = firstIndex.retVal(queryIndex);
            String lastName = lastIndex.retVal(queryIndex);
            String id = idIndex.retVal(queryIndex);
            System.out.println("Found: "+ toString(firstName,lastName,id) +"\n");
        }
    }

    //Various Printing Methods
    public void ListByFirstAscending() {
        print("up","first");
    }

    public void ListByFirstDescending() {
        print("down","first");
    }

    public void ListByLastAscending() {
        print("up","last");
    }

    public void ListByLastDescending() {
        print("down","last");
    }

    public void ListByIDAscending() {
        print("up","id");
    }

    public void ListByIDDescending() {
        print("down","id");
    }

    //Method to Print in various directions
    public void print(String direction, String type) {
        int rover,position;
        String firstName, lastName, id;
        if (direction.equals("up")) {
            switch (type) {
                case "first":
                    for (rover = 0; rover < nextDBRecord; rover++) {
                        position = firstIndex.indexSpot(rover);
                        firstName = firstIndex.print(rover);
                        lastName = lastIndex.retVal(position);
                        id = idIndex.retVal(position);
                        System.out.println(toString(firstName,lastName,id));
                    }
                    break;
                case "last":
                    for (rover = 0; rover < nextDBRecord; rover++) {
                        position = lastIndex.indexSpot(rover);
                        firstName = firstIndex.retVal(position);
                        lastName = lastIndex.print(rover);
                        id = idIndex.retVal(position);
                        System.out.println(toString(firstName,lastName,id));
                    }
                    break;
                case "id":
                    for (rover = 0; rover < nextDBRecord; rover++) {
                        position = idIndex.indexSpot(rover);
                        firstName = firstIndex.retVal(position);
                        lastName = lastIndex.retVal(position);
                        id = idIndex.print(rover);
                        System.out.println(toString(firstName,lastName,id));
                    }
                    break;
                default:
                    System.out.println("Error in printing. Try again.");
            }
        } else {
            switch (type) {
                case "first":
                    for (rover = nextDBRecord-1; rover >= 0; rover--) {
                        position = firstIndex.indexSpot(rover);
                        firstName = firstIndex.print(rover);
                        lastName = lastIndex.retVal(position);
                        id = idIndex.retVal(position);
                        System.out.println(toString(firstName,lastName,id));
                    }
                    break;
                case "last":
                    for (rover = nextDBRecord-1; rover >= 0; rover--) {
                        position = lastIndex.indexSpot(rover);
                        firstName = firstIndex.retVal(position);
                        lastName = lastIndex.print(rover);
                        id = idIndex.retVal(position);
                        System.out.println(toString(firstName,lastName,id));
                    }
                    break;
                case "id":
                    for (rover = nextDBRecord-1; rover >= 0; rover--) {
                        position = idIndex.indexSpot(rover);
                        firstName = firstIndex.retVal(position);
                        lastName = lastIndex.retVal(position);
                        id = idIndex.print(rover);
                        System.out.println(toString(firstName,lastName,id));
                    }
                    break;
                default:
                    System.out.println("Error in printing. Try again.");
            }
        }
        System.out.println();
    }

    public String toString(String fname, String lname, String id) {
        return (lname + " " + fname + " " + id);
    }

    //Method to Delete a student by ID
    public void deleteIt() {
        //Prompts User for ID for deletion
        System.out.println("Enter the ID of the student: ");
        String key = keyb.next();

        //Checks if ID is valid length
        if (idenLength(key)) {
            return;
        }

        //Searches for Index Record
        int where = idIndex.find(key);

        //Checks if ID exists
        if (where == -1) {
            System.out.println("ID not found. Try again.");
        } else {
            //Values of Student for Deletion
            String firstName = firstIndex.retVal(where);
            String lastName = lastIndex.retVal(where);
            String id = idIndex.retVal(where);

            System.out.println("Delete: " + toString(firstName,lastName,id) + " ? Y/N");
            String response = keyb.next().toLowerCase();
            if (response.equals("y")) {
                firstIndex.delete(new IndexRecord(firstName,where));
                lastIndex.delete(new IndexRecord(lastName,where));
                idIndex.delete(new IndexRecord(id,where));
                nextDBRecord--;
                System.out.println("Student Deleted.");
            } else {
                System.out.println("Not Deleting.");
            }
        }
        System.out.println();
    }
}
