public class Question1 {

    final static int arraySize = 10;
    static int[] prev = new int[arraySize];
    static int[] next = new int[arraySize];
    static int[] data = new int[arraySize];
    static int head = 0;
    static int tail = arraySize-1;


    public static void main(String[] args) {
        init();
        // int[] data1 = {19,28,36,94,42};
        // data = data1.clone();
        printLinkedListInArrays();
        selectionSort();
        printLinkedListInArrays();
        System.out.println("\n Checking if list is sorted from head and tail");
        System.out.println(isListSorted());
    }

    private static boolean isListSorted() {
        int pointer = head;
        while(next[pointer] != -1){
            if(data[pointer]>data[next[pointer]]){
                return false;
            }
            pointer = next[pointer];
        }
        pointer = tail;
        while(prev[pointer]!= -1){
            if(data[pointer]<data[prev[pointer]]){
                return false;
            }
            pointer = prev[pointer];
        }
        return true;
    }

    private static void selectionSort() {
        int tortoisePointer = head;
        while(next[tortoisePointer]!= -1){
            int harePointer =next[tortoisePointer];
            int smallestNumberIndex = tortoisePointer;
            while (harePointer!=-1){
                if(data[smallestNumberIndex]>data[harePointer]){
                    smallestNumberIndex = harePointer;
                }
                harePointer = next[harePointer];
            }
            tortoisePointer = next[tortoisePointer];
            if(prev[tortoisePointer]!=smallestNumberIndex)
            swap(prev[tortoisePointer],smallestNumberIndex);
        }
    }

    private static void swap(final int leftIndex, final int rightIndex) {
        //System.out.println("\nSwapping" + data[leftIndex] +" and " + data[rightIndex]);
        if(leftIndex==head){
            head = rightIndex;
        }
        else if(rightIndex==head){
            head = leftIndex;
        }
        if(leftIndex==tail){
            tail = rightIndex;
        }
        else if (rightIndex==tail)
        {
            tail = leftIndex;
        }
        if(next[leftIndex] == rightIndex){
            next[leftIndex] = next[rightIndex];
            prev[rightIndex] = prev[leftIndex];

            //Swapping First and Second
            if(next[rightIndex]!=-1){
                prev[next[rightIndex]] = leftIndex;
            }

            //Swapping tail with one before tail
            if(prev[leftIndex] != -1){
                next[prev[leftIndex]] = rightIndex;
            }


            next[rightIndex] = leftIndex;
            prev[leftIndex] = rightIndex;
        }

        else{
            int tempPrev = prev[rightIndex];
            int tempNext = next[rightIndex];


            //Next and Previous of swappers
            prev[rightIndex] = prev[leftIndex];
            next[rightIndex] = next[leftIndex];
            prev[leftIndex] = tempPrev;
            next[leftIndex] = tempNext;


            //Neighbours of swap
            //Swap with same values as the references are updated.
            if(next[rightIndex]!=-1){
                prev[next[rightIndex]] = rightIndex;
            }
            if(prev[rightIndex]!=-1) {
                next[prev[rightIndex]] = rightIndex;
            }
            if(prev[leftIndex]!=-1){
                next[prev[leftIndex]] = leftIndex;
            }

            if(next[leftIndex]!=-1){
                prev[next[leftIndex]] = leftIndex;
            }

        }






        /*int temp = data[leftIndex];
        data[leftIndex] = data[rightIndex];
        data[rightIndex] = temp;
*/
        //System.out.println("Data post swap ");
        //printLinkedListInArrays();
    }

    static void init(){
        for (int i = 0; i < arraySize; i++) {
            prev[i] = i-1;
            next[i] = i+1;
            data[i] = (int) (Math.random()*100);
        }
        //Corner case
        prev[0] = -1;
        next[arraySize-1] = -1;
    }


    static void printLinkedListInArrays() {

        System.out.println("\n\n\n\nHead is "+ head +" and Tail is "+ tail);


        System.out.println("\n Previous Array");
        for (int i = 0; i < arraySize; i++) {
            System.out.print(/*"prev["+i+"] = "+*/prev[i]+" ");
        }
        System.out.println("\n Data Array");
        for (int i = 0; i < arraySize; i++) {
            System.out.print(/*"data["+i+"] = "+*/data[i]+" ");
        }
        System.out.println("\n Next Array");
        for (int i = 0; i < arraySize; i++) {
            System.out.print(/*"next["+i+"] = "+*/next[i]+" ");
        }
    }







}
