import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        /*
         * You are to implement the 3 advanced sorting algorithms discussed in class:
         * heapsort, mergesort and quicksort. For each sorting algorithm, you must run
         * it on the following three datasets. You are free to copy the code from the
         * book or any other source. You are to **time** how long each sort took. We
         * will discuss how to do this in class. You only need to hand in a table of
         * the run times, something like the following
         */

        //Creating the Nine arrays of data
        int[] ascendingHeap = new int[10000];
        int[] ascendingMerge = new int[10000];
        int[] ascendingQuick = new int[10000];
        int[] descendingHeap = new int[10000];
        int[] descendingMerge = new int[10000];
        int[] descendingQuick = new int[10000];
        int[] randomHeap = new int[10000];
        int[] randomMerge = new int[10000];
        int[] randomQuick = new int[10000];

        //Creating the three heap tables
        Heap heapAscend = new Heap(10000);
        Heap heapDescend = new Heap(10000);
        Heap heapRandom = new Heap(10000);

        //Variables
        int i;
        Scanner keyb = new Scanner(System.in);

        //Creates the Nano variables
        long ascendHeapStart, ascendHeapEnd, ascendHeapTotal;
        long ascendMergeStart, ascendMergeEnd, ascendMergeTotal;
        long ascendQuickStart, ascendQuickEnd, ascendQuickTotal;
        long descendHeapStart, descendHeapEnd, descendHeapTotal;
        long descendMergeStart, descendMergeEnd, descendMergeTotal;
        long descendQuickStart, descendQuickEnd, descendQuickTotal;
        long randomHeapStart, randomHeapEnd, randomHeapTotal;
        long randomMergeStart, randomMergeEnd, randomMergeTotal;
        long randomQuickStart, randomQuickEnd, randomQuickTotal;

        //File input streams
        FileInputStream ais = null; //Input stream to read the Ascending file
        FileInputStream dis = null; //Input stream to read the Descending file
        FileInputStream ris = null; //Input stream to read the Random file

        //File Printer Writers
        PrintWriter fosAscendHeap = null, fosAscendMerge = null, fosAscendQuick = null;
        PrintWriter fosDescendHeap = null, fosDescendMerge = null, fosDescendQuick = null;
        PrintWriter fosRandHeap = null, fosRandMerge = null, fosRandQuick = null;

        //Random Number Generator
        Random rand = new Random();
        int randValue = rand.nextInt(1000000000);

        //Times File Printer Writer
        PrintWriter fosTIME = null;
        String timeFile =  "AAA-Time-File-" + randValue + ".txt";

        //Try to connect to the Ascending data file
        try {
            ais = new FileInputStream("/Users/deren/Library/Mobile Documents/com~apple~CloudDocs/" +
                    "iCloudProgramming/2021-Winter/311-Java/ProjectFour4/AAAA-AscendingData.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error reading the file.");
            e.printStackTrace();
        }
        Scanner aHandle = new Scanner(ais);
        for(i = 0; i < 10000; i++) {
            int ii = aHandle.nextInt();
            ascendingHeap[i] = ii;
            ascendingMerge[i] = ii;
            ascendingQuick[i] = ii;
        }

        //Try to connect to the Descending data file
        try {
            dis = new FileInputStream("/Users/deren/Library/Mobile Documents/com~apple~CloudDocs/" +
                    "iCloudProgramming/2021-Winter/311-Java/ProjectFour4/AAAA-DescendingData.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error reading the file.");
            e.printStackTrace();
        }
        Scanner dHandle = new Scanner(dis);
        for(i = 0; i < 10000; i++) {
            int ii = dHandle.nextInt();
            descendingHeap[i] = ii;
            descendingMerge[i] = ii;
            descendingQuick[i] = ii;
        }

        //Try to connect to the Random data file
        try {
            ris = new FileInputStream("/Users/deren/Library/Mobile Documents/com~apple~CloudDocs/" +
                    "iCloudProgramming/2021-Winter/311-Java/ProjectFour4/AAAA-RandomData.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error reading the file.");
            e.printStackTrace();
        }
        Scanner rHandle = new Scanner(ris);
        for(i = 0; i < 10000; i++) {
            int ii = rHandle.nextInt();
            randomHeap[i] = ii;
            randomMerge[i] = ii;
            randomQuick[i] = ii;
        }

        //HEAP SORT PROCESS ***************************************************************
        //Add the array items to the arrays
        for (int qq = 0; qq < 10000; qq++) {
            heapAscend.insert(ascendingHeap[qq]);
            heapDescend.insert(descendingHeap[qq]);
            heapRandom.insert(randomHeap[qq]);
        }

        //Sort the ascending data heap
        ascendHeapStart = System.nanoTime();
        heapAscend.sort();
        ascendHeapEnd = System.nanoTime();
        ascendHeapTotal = ascendHeapEnd - ascendHeapStart;

        //Sort the descending data heap
        descendHeapStart = System.nanoTime();
        heapDescend.sort();
        descendHeapEnd = System.nanoTime();
        descendHeapTotal = descendHeapEnd - descendHeapStart;

        //Sort the random data heap
        randomHeapStart = System.nanoTime();
        heapRandom.sort();
        randomHeapEnd = System.nanoTime();
        randomHeapTotal = randomHeapEnd - randomHeapStart;
        //************************************************************************************

        //MERGE SORT PROCESS ***************************************************************
        //Sort the Ascending data
        ascendMergeStart = System.nanoTime();
        mergeSort(ascendingMerge,0,ascendingMerge.length);
        ascendMergeEnd = System.nanoTime();
        ascendMergeTotal = ascendMergeEnd - ascendMergeStart;

        //Sort the Descending data
        descendMergeStart = System.nanoTime();
        mergeSort(descendingMerge,0,descendingMerge.length);
        descendMergeEnd = System.nanoTime();
        descendMergeTotal = descendMergeEnd - descendMergeStart;

        //Sort the Random data
        randomMergeStart = System.nanoTime();
        mergeSort(randomMerge,0,randomMerge.length);
        randomMergeEnd = System.nanoTime();
        randomMergeTotal = randomMergeEnd - randomMergeStart;

        //************************************************************************************

        //QUICK SORT PROCESS ***************************************************************
        //Sort the Ascending data
        ascendQuickStart = System.nanoTime();
        quickSort(ascendingQuick,0,ascendingQuick.length);
        ascendQuickEnd = System.nanoTime();
        ascendQuickTotal = ascendQuickEnd - ascendQuickStart;

        //Sort the Descending data
        descendQuickStart = System.nanoTime();
        quickSort(descendingQuick,0,descendingQuick.length);
        descendQuickEnd = System.nanoTime();
        descendQuickTotal = descendQuickEnd - descendQuickStart;

        //Sort the Random data
        randomQuickStart = System.nanoTime();
        quickSort(randomQuick,0,randomQuick.length);
        randomQuickEnd = System.nanoTime();
        randomQuickTotal = randomQuickEnd - randomQuickStart;
        //************************************************************************************

        //Output the times
        System.out.println("Arrays created and sorted."+"\n");
        System.out.println("Times (nano seconds): ");
        System.out.print("\t\t\t\t\t\t"+ "Ascending" +"\t\t\t\t\t"+ "Descending" +"\t\t\t\t\t"+ "Random"+"\n");
        System.out.print("Merge"+"\t\t\t\t\t"+ascendMergeTotal+"\t\t\t\t\t\t"+descendMergeTotal+"\t\t\t\t\t\t"+randomMergeTotal+"\n");
        System.out.print("Heap"+"\t\t\t\t\t"+ascendHeapTotal+"\t\t\t\t\t\t"+descendHeapTotal+"\t\t\t\t\t\t"+randomHeapTotal+"\n");
        System.out.println("Quick"+"\t\t\t\t\t"+ascendQuickTotal+"\t\t\t\t\t"+descendQuickTotal+"\t\t\t\t\t"+randomQuickTotal+"\n");


        //Request user to enter the file name format
        System.out.println("Enter file name: ");
        String ogFile = keyb.next();

        //Create the 9 file names
        String ascendHeapFile = ogFile + "-Ascending-Heap.txt";
        String ascendMergeFile = ogFile + "-Ascending-Merge.txt";
        String ascendQuickFile = ogFile + "-Ascending-Quick.txt";
        String descendHeapFile = ogFile + "-Descending-Heap.txt";
        String descendMergeFile = ogFile + "-Descending-Merge.txt";
        String descendQuickFile = ogFile + "-Descending-Quick.txt";
        String randomHeapFile = ogFile + "-Random-Heap.txt";
        String randomMergeFile = ogFile + "-Random-Merge.txt";
        String randomQuickFile = ogFile + "-Random-Quick.txt";

        //Create the 9 files
        try {
            fosAscendHeap = new PrintWriter(new FileOutputStream(ascendHeapFile));
            fosAscendMerge = new PrintWriter(new FileOutputStream(ascendMergeFile));
            fosAscendQuick = new PrintWriter(new FileOutputStream(ascendQuickFile));
            fosDescendHeap = new PrintWriter(new FileOutputStream(descendHeapFile));
            fosDescendMerge = new PrintWriter(new FileOutputStream(descendMergeFile));
            fosDescendQuick = new PrintWriter(new FileOutputStream(descendQuickFile));
            fosRandHeap = new PrintWriter(new FileOutputStream(randomHeapFile));
            fosRandMerge = new PrintWriter(new FileOutputStream(randomMergeFile));
            fosRandQuick = new PrintWriter(new FileOutputStream(randomQuickFile));

            fosTIME = new PrintWriter(new FileOutputStream(timeFile));
        } catch (IOException e) {
            System.out.println("Error Occurred");
            e.printStackTrace();
        }

        //Write the values to the files
        for (int qq = 0; qq < 10000; qq++) {
            //Ascending Files
            fosAscendHeap.println(heapAscend.printValueAt(qq));
            fosAscendMerge.println(ascendingMerge[qq]);
            fosAscendQuick.println(ascendingQuick[qq]);

            //Descending Files
            fosDescendHeap.println(heapDescend.printValueAt(qq));
            fosDescendMerge.println(descendingMerge[qq]);
            fosDescendQuick.println(descendingQuick[qq]);

            //Random Files
            fosRandHeap.println(heapRandom.printValueAt(qq));
            fosRandMerge.println(randomMerge[qq]);
            fosRandQuick.println(randomQuick[qq]);
        }

        //Close the scanners
        fosAscendHeap.close();
        fosAscendMerge.close();
        fosAscendQuick.close();
        fosDescendHeap.close();
        fosDescendMerge.close();
        fosDescendQuick.close();
        fosRandHeap.close();
        fosRandMerge.close();
        fosRandQuick.close();

        //Write Times to a File
        fosTIME.println("Results in nano seconds:");
        fosTIME.print("\t\t\t\t\t\t"+ "Ascending" +"\t\t\t\t\t"+ "Descending" +"\t\t\t\t\t"+ "Random"+"\n");
        fosTIME.print("Merge"+"\t\t\t\t\t"+ascendMergeTotal+"\t\t\t\t\t\t"+descendMergeTotal+"\t\t\t\t\t\t"+randomMergeTotal+"\n");
        fosTIME.print("Heap"+"\t\t\t\t\t"+ascendHeapTotal+"\t\t\t\t\t\t"+descendHeapTotal+"\t\t\t\t\t\t"+randomHeapTotal+"\n");
        fosTIME.println("Quick"+"\t\t\t\t\t"+ascendQuickTotal+"\t\t\t\t\t"+descendQuickTotal+"\t\t\t\t\t"+randomQuickTotal+"\n");

        fosTIME.close();

    }

    public static void mergeSort(int[] input, int start, int end) {
        if(end - start < 2) {
            return;
        }

        int mid = (start + end) / 2;
        mergeSort(input, start,mid);
        mergeSort(input,mid,end);
        merge(input, start, mid, end);
    }

    public static void merge(int[] input, int start, int mid, int end) {
        if (input[mid - 1] <= input[mid]) {
            return;
        }

        int i = start;
        int j = mid;
        int tempIndex = 0;

        int[] temp = new int[end - start];

        while ((i < mid) && (j < end)) {
            temp[tempIndex++] = input[i] <= input[j] ? input[i++] : input[j++];
        }

        System.arraycopy(input, i, input, start + tempIndex, mid - i);
        System.arraycopy(temp, 0, input, start, tempIndex);
    }

    public static void quickSort(int[] input, int start, int end) {
        if ((end - start) < 2) {
            return;
        }

        int pivotIndex = partition(input, start, end);
        quickSort(input, start, pivotIndex);
        quickSort(input, pivotIndex+1,end);
    }

    public static int partition(int[] input, int start, int end) {
        int pivot = input[start];
        int i = start;
        int j = end;

        while(i < j) {
            //Empty loop body
            while ((i < j) && input[--j] >= pivot);
            if (i < j) {
                input[i] = input[j];
            }

            //Empty loop body
            while ((i < j) && input[++i] <= pivot);
            if (i < j) {
                input[j] = input[i];
            }
        }
        input[j] = pivot;
        return j;
    }
}


/*
 * Just need to hand in a piece of paper with the 9 values.
 * Still needs to see the program run.
 *
 * Read the data into an array
 *      Start Timer
 *  sort array
 *      end timer
 * Write array to file
 *
 * Java get time in milliseconds
 * Java get nano seconds    //preferred
 *
 *
 * Create 9 output files (the 9 data items)
 * FileNameMergeAscend
 * FileNameQuickAscend
 * FileNameHeapAscend
 * etc
 */
