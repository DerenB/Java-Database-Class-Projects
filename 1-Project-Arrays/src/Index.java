public class Index {
    private IndexRecord[] data;
    private int nElems;

    public Index() {
        data = new IndexRecord[100];
        nElems = 0;
    }

    public Index(int sz) {
        data = new IndexRecord[sz];
        nElems = 0;
    }

    //Insertion Loop
    public void insert(IndexRecord newVal, int position) {
        int rover;
        for (rover = nElems - 1; rover >=0; rover--) {
            if (newVal.compareTo(data[rover]) > 0) break;
            data[rover + 1] = data[rover];
        }
        data[rover + 1] = newVal;
        nElems++;
    }

    //Find String value in IndexRecord
    public int find(String key) {
        int low = 0, mid = 0;
        int high = nElems-1;

        while (low <= high) {
            mid = (low + high) /2;
            if (data[mid].getValue().compareTo(key)==0) break;
            if (key.compareTo(data[mid].getValue()) < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return (low <= high ? data[mid].getPosition() : -1);
    }

    //Checks for duplicate IDs
    public boolean duplicate(String key) {
        boolean duped = false;
        for (int i = nElems; i > 0; i--) {
            if (key.compareTo(data[i-1].getValue()) == 0) {
                duped = true;
                break;
            }
        }
        return duped;
    }

    //Find IndexRecord in Index
    public int find(IndexRecord key) {
        int low = 0, mid = 0;
        int high = nElems - 1;
        while(low <= high) {
            mid = (low + high) / 2;
            if (data[mid].compareTo(key)==0) break;
            if (key.compareTo(data[mid]) < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return (low <= high ? mid : -1);
    }

    //Return String Value of specific index
    public String retVal(int key) {
        String output = "";
        for (int i = 0; i < nElems; i++) {
            if(data[i].getPosition() == key) {
                output = data[i].getValue();
                break;
            } else {
                output = "***error***";
            }
        }
        return output;
    }

    //Return IndexRecord String Value at spot in index
    public String print(int spot) {
        return data[spot].getValue();
    }

    //Return IndexRecord Index Value
    public int indexSpot(int spot) {
        return data[spot].getPosition();
    }

    public boolean delete(IndexRecord delVal) {
        int rover, where;
        where = find(delVal);
        if (where != -1) {
            for (rover = where+1; rover <= nElems-1; rover++) {
                data[rover-1] = data[rover];
            }
            nElems--;
        }
        return (where == -1);
    }
}
