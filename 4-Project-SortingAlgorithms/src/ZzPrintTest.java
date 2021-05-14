import java.io.*;
import java.util.Scanner;

public class ZzPrintTest {
    public static void main(String[] args) {
        Scanner keyb = new Scanner(System.in);
        PrintWriter fos = null;

        int[] myArray = {1,2,3,4,5,6,7,8,9};

        System.out.println("Enter the file name: ");
        String userInput = keyb.next();

        String fileName1 = userInput + ".txt";
        String fileName2 = userInput + ".txt";

        try {
//            FileWriter myObj = new FileWriter(fileName);
//            for(int i = 0; i < myArray.length; i++) {
//                myObj.write(myArray[i]);
//            }
//            myObj.close();

//            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
//            for(int i = 0; i < myArray.length; i++) {
//                writer.write(myArray[i]);
//            }
//            writer.close();

//            FileOutputStream outStream = new FileOutputStream(fileName);
//            DataOutputStream dataStream = new DataOutputStream(new BufferedOutputStream(outStream));
//            dataStream.writeByte(5);
//            dataStream.close();

            fos = new PrintWriter(new FileOutputStream(fileName1));


        } catch (IOException e) {
            System.out.println("Error occured.");
            e.printStackTrace();
        }

        for(int i = 0; i < myArray.length; i++) {
            fos.println(myArray[i]);
        }
        fos.close();

    }
}
