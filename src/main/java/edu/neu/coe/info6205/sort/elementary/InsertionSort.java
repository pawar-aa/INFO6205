/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Config;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Random;

public class InsertionSort<X extends Comparable<X>> extends SortWithHelper<X> {

    /**
     * Constructor for any sub-classes to use.
     *
     * @param description the description.
     * @param N           the number of elements expected.
     * @param config      the configuration.
     */
    protected InsertionSort(String description, int N, Config config) {
        super(description, N, config);
    }

    /**
     * Constructor for InsertionSort
     *
     * @param N      the number elements we expect to sort.
     * @param config the configuration.
     */
    public InsertionSort(int N, Config config) {
        this(DESCRIPTION, N, config);
    }

    public InsertionSort(Config config) {
        this(new BaseHelper<>(DESCRIPTION, config));
    }

    /**
     * Constructor for InsertionSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public InsertionSort(Helper<X> helper) {
        super(helper);
    }

    public InsertionSort() {
        this(BaseHelper.getHelper(InsertionSort.class));
    }

    /**
     * Sort the sub-array xs:from:to using insertion sort.
     *
     * @param xs   sort the array xs from "from" to "to".
     * @param from the index of the first element to sort
     * @param to   the index of the first element not to sort
     */
    public void sort(X[] xs, int from, int to) {
        final Helper<X> helper = getHelper();

        // FIXME
        for(int i=from;i<to;i++){
            int k = i;
            while(k>0 && helper.swapStableConditional(xs,k--));
        }
        // END 
    }

    public static final String DESCRIPTION = "Insertion sort";

    public static <T extends Comparable<T>> void sort(T[] ts) {
        new InsertionSort<T>().mutatingSort(ts);
    }

    public static void main(String[] args){
        InsertionSort insertionSort = new InsertionSort();
        Random random = new Random();

        ArrayList<Integer> array_random = new ArrayList<>();
        ArrayList<Integer> array_ordered = new ArrayList<>();
        ArrayList<Integer> array_reverse = new ArrayList<>();
        ArrayList<Integer> array_partially_ordered = new ArrayList<>();

        for(int i=2000;i<35000;i=i*2){

            for(int j=0;j<i;j++) array_random.add(random.nextInt(i));
            for(int j=0;j<i;j++) array_ordered.add(j+1);
            for(int j=0;j<i;j++) array_reverse.add(j-1);
            for(int j=0;j<i;j++) {
                if(j>i/2) array_partially_ordered.add(random.nextInt(i));
                else array_partially_ordered.add(j);
            }

            Integer[] m_array_random = array_random.toArray(new Integer[0]);
            Integer[] m_array_ordered = array_ordered.toArray(new Integer[0]);
            Integer[] m_array_reverse = array_reverse.toArray(new Integer[0]);
            Integer[] m_array_partially_ordered = array_partially_ordered.toArray(new Integer[0]);

            Benchmark<Boolean>benchmarkRandom = new Benchmark_Timer<>("randomSort",b->{
                insertionSort.sort(m_array_random.clone(), 0, m_array_random.length);
            });
            double resultRandom = benchmarkRandom.run(true, 10);

            Benchmark<Boolean>benchmarkOrdered = new Benchmark_Timer<>("orderedSort",b->{
                insertionSort.sort(m_array_ordered.clone(), 0, m_array_ordered.length);
            });
            double resultOrdered = benchmarkOrdered.run(true, 10);

            Benchmark<Boolean>benchmarkReverse = new Benchmark_Timer<>("reverseSort",b->{
                insertionSort.sort(m_array_reverse.clone(), 0, m_array_reverse.length);
            });
            double resultReverse = benchmarkReverse.run(true, 10);

            Benchmark<Boolean>benchmarkPartiallyOrdered = new Benchmark_Timer<>("partiallyOrderedSort",b->{
                insertionSort.sort(m_array_partially_ordered.clone(), 0, m_array_partially_ordered.length);
            });
            double resultPartiallyOrdered = benchmarkPartiallyOrdered.run(true, 10);

            System.out.println("When array size is: " + i);
            System.out.println("Random: " + resultRandom);
            System.out.println("Ordered: " + resultOrdered);
            System.out.println("Reverse: " + resultReverse);
            System.out.println("Partially Ordered: " + resultPartiallyOrdered + "\n\n");
        }

    }
}
