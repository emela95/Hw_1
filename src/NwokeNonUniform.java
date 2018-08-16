import java.util.Random;

/**
 * SER222 Assignment
 * program to demonstrate benchmarking algorithm using insertion and selection sorts
 *
 * Author: Nwoke chiemela, Acuna, Sedgewick & Wayne
 * version: 1.1
 * Date: 7/5/18
 *
 *
 * */

public class NwokeNonUniform {
    //numbers for filling arrays
    private static final int num1 = 2048;
    private static final int num2 = 4096;



    public static class InsertionSort {
        public void Insort(int[] a) {
            int N = a.length;

            for (int i = 1; i < N; i++) {
                // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
                for (int j = i; j > 0 && less(a[j], a[j - 1]); j--)
                    exch(a, j, j - 1);
            }
        }


        ////methods below this point come from SortPlatform
        private boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }

        private void exch(int[] a, int i, int j) {
            Comparable t = a[i];
            a[i] = a[j];
            a[j] = (int) t;
        }

        private void show(Comparable[] a) {
            for (int i = 0; i < a.length; i++)
                System.out.print(a[i] + " ");
            System.out.println();
        }

        public boolean isSorted(int[] a) {
            for (int i = 1; i < a.length; i++)
                if (less(a[i], a[i - 1]))
                    return false;

            return true;
        }
    }


    public static class SelectionSort {
        public void Ssort(int[] a) {
            int N = a.length;

            for (int i = 0; i < N; i++) {
                // Exchange a[i] with smallest entry in a[i+1...N).
                int min = i; // index of smallest entry.
                for (int j = i + 1; j < N; j++)
                    if (less(a[j], a[min])) min = j;
                exch(a, i, min);
            }
        }

        //methods below this point come from SortPlatform
        private boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }

        private void exch(int[] a, int i, int j) {
            Comparable t = a[i];
            a[i] = a[j];
            a[j] = (int) t;
        }

        private void show(Comparable[] a) {
            for (int i = 0; i < a.length; i++)
                System.out.print(a[i] + " ");
            System.out.println();
        }

        public boolean isSorted(Comparable[] a) {
            for (int i = 1; i < a.length; i++)
                if (less(a[i], a[i - 1]))
                    return false;

            return true;
        }
    }


    /**
     * A utility class to measure the running time (wall clock) of a program.
     *
     * @author Sedgewick
     * @author Wayne
     * @version 1.0
     */
    public static class Stopwatch {

        private final long start;

        /**
         * Initializes a new stopwatch.
         */
        public Stopwatch() {
            start = System.currentTimeMillis();
        }

        /**
         * Returns the elapsed CPU time (in seconds) since the stopwatch was created.
         *
         * @return elapsed CPU time (in seconds) since the stopwatch was created
         */
        public double elapsedTime() {
            long now = System.currentTimeMillis();
            return (now - start) / 1000.0;
        }


    }


    //fill half the array with 0's and the other half with 1's
    private static int[] fill1(int n){
        int arr[] = new int[n];
        for(int i = 0; i < n/2; i++){
            arr[i] = 0;
        }
        for(int i = n/2; i < n; i++){
            arr[i] = 1;
        }
        shuffle(arr);
        return arr;
    }


    //fill the array for half the data 0's, half the remainder is 1's, half the remainder is 2's, 3's ....
    private static int[] fill2(int n){
        int arr[] = new int[n];
        for (int i = 0; i < n/2; i++)
            arr[i] = 0;
        for (int i = n/2; i < (int)(0.75*n); i++){
            arr[i] = 1;
        }


        for (int i = (int)(0.75*n); i < n; i++){
            arr[i] = 2;
        }
        shuffle(arr);
        return arr;
    }





    //fill the array with half 0's half random int values 0 to 9
    private static int[] fill3(int n){
        int arr[] = new int[n];
        Random randdd = new Random();
        for(int i = 0; i < n/2; i++){
            arr[i] = 0;
        }

        for(int i = n/2; i < n; i++){
            arr[i] = randdd.nextInt(Integer.MAX_VALUE);
        }
        shuffle(arr);
        return arr;
    }




    //method used to shuffle the array
    public static void shuffle(int[] x){
        int index;
        int temp;
        Random rand = new Random();
        for (int i = x.length - 1; i > 0; i--){
            index = rand.nextInt(i + 1);
            temp = x[index];
            x[index] = x[i];
            x[i] = temp;
        }
    }



    /**
     * Basic helper functions for implementing a sorting algorithm.
     *
     * @author Sedgewick and Wayne, Acuna
     * @version 1.0
     */

    public static class SortPlatform {
        public void sort(Comparable[] a) {
            //
        }

        private boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }

        private void exch(Comparable[] a, int i, int j) {
            Comparable t = a[i];
            a[i] = a[j];
            a[j] = t;
        }

        private void show(Comparable[] a) {
            for (int i = 0; i < a.length; i++)
                System.out.print(a[i] + " ");
            System.out.println();
        }

        public boolean isSorted(Comparable[] a) {
            for (int i = 1; i < a.length; i++)
                if (less(a[i], a[i - 1]))
                    return false;

            return true;
        }


    }

    //testing the various methods
    //12 total test were carried out on insertion and selection sorts (6 each)
    // 2 array sizes for 3 methods created

    public static void main(String args[]) {

        /**
         * Hypothesis: for insertion sort, the big-O time will be O(n)
         * since function1 is made up of 0s and 1s
         *
         *
         * */

        System.out.println("===============METHOD SET ONE (Insertion Sort)===================");

        InsertionSort insert = new InsertionSort();
        int[] data1 = fill1(num1);


        Stopwatch stopwatch1 = new Stopwatch();
        insert.Insort(data1);
        double time1 = stopwatch1.elapsedTime();
        insert.isSorted(data1);
        System.out.println(time1);


        data1 = fill1(num2);
        Stopwatch stopwatch2 = new Stopwatch();
        insert.Insort(data1);
        double time2 = stopwatch2.elapsedTime();
        System.out.println(time2);

        double b1 = (Math.log(time1/time2)); //doubling formula
        System.out.println("Doubling formula result is: "+ b1);


        /**
         * Hypothesis for half the data 0's, half the remainder is 1's, half the remainder is 2's, 3's ....
         * the runtime should be O(n^2)
         *
         *
         * */
        System.out.println();
        System.out.println("===============METHOD SET TWO (Insertion Sort)===================");
        int[] data2 = fill2(num1);
        Stopwatch stopwatch3 = new Stopwatch();
        insert.Insort(data2);
        double time3 = stopwatch3.elapsedTime();
        System.out.println(time3);

        data2 = fill2(num2);
        Stopwatch stopwatch4 = new Stopwatch();
        insert.Insort(data2);
        double time4 = stopwatch4.elapsedTime();
        System.out.println(time4);

        double b2 = (Math.log(time3/time4)); //doubling formula
        System.out.println("Doubling formula result is: "+b2);


        /**
         *
         * Hypothesis for the array with half 0's half random int values 0 to 9
         * using insertion sort, the runtime should be O(n^2)
         *
         *
         * */

        System.out.println();
        System.out.println("===============METHOD SET THREE (Insertion Sort)===================");

        int[] data3 = fill3(num1);
        Stopwatch stopwatch5 = new Stopwatch();
        insert.Insort(data3);
        double time5 = stopwatch5.elapsedTime();
        System.out.println(time5);

        data3 = fill3(num2);
        Stopwatch stopwatch6 = new Stopwatch();
        insert.Insort(data3);
        double time6 = stopwatch6.elapsedTime();
        System.out.println(time6);

        double b3 = (Math.log(time5/time6)); //doubling formula
        System.out.println("Doubling formula result is: "+b3);

        System.out.println();
        System.out.println("############ Selection Sort ##########");
        SelectionSort select = new SelectionSort();

        /**
         * Hypotheis: using selection sort for this data, the runtime
         * will be O(n^2) because of the order in which the data is being sorted
         *
         *
         * */
        System.out.println("===============METHOD SET ONE (Selection Sort)===================");

        int[] data4 = fill1(num1);
        Stopwatch stopwatch7 = new Stopwatch();
        select.Ssort(data4);
        double time7 = stopwatch7.elapsedTime();
        System.out.println(time7);

        data4 = fill1(num2);
        Stopwatch stopwatch8 = new Stopwatch();
        select.Ssort(data4);
        double time8 = stopwatch8.elapsedTime();
        System.out.println(time8);

        double b4 = (Math.log(time7/time8)); //doubling formula
        System.out.println("Doubling formula result is: "+b4);





        System.out.println();
        System.out.println("===============METHOD SET TWO (Selection Sort)===================");
        /**
         * Hypotheis: using selection sort for this data, the runtime
         * will be O(n^2) because of the order in which the data is being sorted
         *
         *
         * */

        int[] data5 = fill2(num1);
        Stopwatch stopwatch9 = new Stopwatch();
        select.Ssort(data5);
        double time9 = stopwatch9.elapsedTime();
        System.out.println(time9);

        data5 = fill2(num2);
        Stopwatch stopwatch10 = new Stopwatch();
        select.Ssort(data5);
        double time10 = stopwatch10.elapsedTime();
        System.out.println(time10);

        double b5 = (Math.log(time9/time10)); //doubling formula
        System.out.println("Doubling formula result is: "+b5);



        System.out.println();
        System.out.println("===============METHOD SET THREE (Selection Sort)===================");
        /**
         * Hypotheis: using selection sort for this data, the runtime
         * will be O(n^2) because of the order in which the data is being sorted
         *
         *
         * */


        int[] data6 = fill3(num1);
        Stopwatch stopwatch11 = new Stopwatch();
        select.Ssort(data6);
        double time11 = stopwatch11.elapsedTime();
        System.out.println(time11);

        data6 = fill3(num2);
        Stopwatch stopwatch12 = new Stopwatch();
        select.Ssort(data6);
        double time12 = stopwatch12.elapsedTime();
        System.out.println(time12);

        double b6 = (Math.log(time11/time12)); //doubling formula
        System.out.println("Doubling formula result is: "+b6);


    }


}

