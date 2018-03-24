public class Question2 {
    //static int arraySize = 10;
    private static int swapCount =0;


    public static void main(String[] args) {
        System.out.println("");
        //Testing with 3 input sizes
        testSort(100);
        testSort(1000);
        testSort(10000);
    }

    static void testSort(int arraySize){
        //Initialize Arrays
        int[] copyOfInput,inputArray = new int[arraySize];
        resetStatsAndInputArray(inputArray);
        //Copy to keep the input same for each sort for fair chance.
        copyOfInput = inputArray.clone();

        System.out.println("Array Size is "+arraySize);


        //MergeSort
        long startTime = System.currentTimeMillis();
        swapCount =0;
        mergeSort(0,inputArray.length,inputArray);
        long mergeSortTime = System.currentTimeMillis()-startTime;
        printStats("MergeSort",mergeSortTime);

        //InsertionSort
        inputArray = copyOfInput.clone();
        swapCount =0;
        startTime = System.currentTimeMillis();
        insertionSort(inputArray);
        long insertionSortTime = System.currentTimeMillis()-startTime;
        printStats("Insertion Sort",insertionSortTime);
        //System.out.println(isSorted(inputArray));


        //BubbleSort
        inputArray = copyOfInput.clone();
        swapCount = 0;
        startTime = System.currentTimeMillis();
        bubbleSort(inputArray);
        long bubbleSortTime = System.currentTimeMillis()-startTime;
        printStats("Bubble Sort", bubbleSortTime);
        System.out.println("\n\n");

    }

    //Logic For bubble sort
    private static void bubbleSort(int[] inputArray) {
        for (int i = 0; i < inputArray.length-1; i++) {
            for (int j = 0; j < inputArray.length-i-1; j++) {
                if(inputArray[j]>inputArray[j+1])
                    swapInArray(inputArray,j,j+1);
            }
        }
    }

    //Method to print sort statistics.
    private static void printStats(String name , long sortTime) {
        System.out.println("************************   "+name.toUpperCase()+ "    ************************");

        System.out.println("Number of Swaps is : "+ swapCount);
        System.out.println("Time Taken : "+sortTime+"ms");
    }

    //Insertion Sort Logic
    private static void insertionSort(int[] inputArray) {
        for (int mainIterator = 0; mainIterator < inputArray.length; mainIterator++) {
            int sortedPartIndex = mainIterator;
            //int temp = inputArray[mainIterator];
            while (sortedPartIndex >0 && inputArray[sortedPartIndex]<inputArray[sortedPartIndex-1]) {
                swapInArray(inputArray,sortedPartIndex,sortedPartIndex-1);
                sortedPartIndex--;
            }
        }
    }

    //Method to swap elements in an array at given indices.
    private static void swapInArray(int[] inputArray, int firstIndex, int secondIndex) {
        int temp = inputArray[firstIndex];
        inputArray[firstIndex] = inputArray[secondIndex];
        inputArray[secondIndex]= temp;
        swapCount++;
    }

    private static boolean isSorted(int[] inputArray) {
        for (int i = 0; i < inputArray.length-1; i++) {
            if(inputArray[i]>inputArray[i+1]){
                return false;
            }
        }
        return true;
    }

    private static void mergeSort(int left, int right, int[] inputArray) {
        int mid = (left+right)/2;
        if(right-left>1){
            mergeSort(left,mid,inputArray);
            mergeSort(mid,right,inputArray);
            merge(left,mid,right,inputArray);
        }
    }

    private static void merge(int lowerBound, int mid, int upperBound, int[] inputArray) {
        int tempArray[] = new int[upperBound-lowerBound];
        int index = 0;
        int leftIterator = lowerBound;
        int rightIterator = mid;
        //Merge Logic
        while(leftIterator < mid && rightIterator < upperBound){
            //Check to pick from left array or right
            if(inputArray[leftIterator] <= inputArray[rightIterator]){
                tempArray[index] = inputArray[leftIterator];
                leftIterator++;
            }
            else{
                tempArray[index] = inputArray[rightIterator];
                rightIterator++;
            }
            index++;
        }
        //Left Over Items in left array, if any
        while(leftIterator < mid){ tempArray[index] = inputArray[leftIterator];  index++; leftIterator++; }
        //Left Over Items in right array, if any
        while(rightIterator < upperBound){tempArray[index] = inputArray[rightIterator]; index++; rightIterator++;}

        //Putting data back in Array
        for(int iterator = 0; iterator < tempArray.length;iterator++)
            inputArray[lowerBound+iterator] = tempArray[iterator];
    }

    //Method to reset array, and additional params.
    private static void resetStatsAndInputArray(int[] inputArray) {
        swapCount = 0;
        for (int i = 0; i < inputArray.length; i++) {
            inputArray[i] = (int) (Math.random()*1000);
        }
    }
}
