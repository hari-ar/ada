public class Question2 {
    static int arraySize = 100;
    public static void main(String[] args) {
        testSort();
    }

    static void testSort(){
        int[] inputArray = new int[arraySize];
        populateArray(inputArray);
        mergeSort(0,inputArray.length,inputArray);
        System.out.println(isSorted(inputArray));
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
        //
        while(leftIterator < mid && rightIterator < upperBound){
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


    private static void populateArray(int[] inputArray) {
        for (int i = 0; i < inputArray.length; i++) {
            inputArray[i] = (int) (Math.random()*1000);
        }
    }
}
