# Java-Database-Class-Projects
Java projects written for EMU class Databases &amp; Algorithms

In Winter 2021 I took COSC-311: Algorithms and Data Structures at EMU.
The class was mainly based on four projects projects:

Project 1: Create a database that stores student information consisting of First Name, Last Name, and ID. The three pieces of data are stored in three separate arrays in order and with an index. The data is provided by the professor in a data file and stored as strings.
	    Classes:
    1) COSC311Driver: Main class of the project, supplied by the professor and could not be modified.
    2) DataBase: Reads in and manipulates the data. Has all of the methods that the Driver program calls.
    3) DataBaseRecord: An unordered array of all of the students [firstName, lastName, id]. Once added to this array, students are never deleted.
    4) Index: Ordered Array for each of the three student data points. Uses IndexRecord to link the three separate arrays of data together.
    5) IndexRecord: Index value and data value at Index.

Project 2: The same as Project 1 except using Linked Lists instead of Ordered Arrays.

Project 3: The same as Projects 1 and 2 except using Threaded Binary Trees. 

Project 4: Three sorting algorithms sort three sets of data. Heap Sort, Merge Sort, and Quick Sort are used to sort the three sets of data which are in ascending order, descending order, and random order. All nine sort calls are timed in nanoseconds.
