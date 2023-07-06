package datastructures.algo;

import datastructures.Driver;
import datastructures.util.Generator;
import datastructures.util.StopWatch;
import java.time.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SorterTest{

    private Sorter<Integer> integerSorter;

    private ArrayList<Integer> arrEmpty;
    private ArrayList<Integer> arrOneElement;
    private ArrayList<Integer> arrIncreasing;
    private ArrayList<Integer> arrNonIncreasing;
    private ArrayList<Integer> arrDecreasing;
    private ArrayList<Integer> arrNonDecreasing;
    private ArrayList<Integer> arrRepeating;
    private ArrayList<Integer> arrUnorderedNoDuplicate;
    private ArrayList<Integer> arrUnorderedWithDuplicate;


    @BeforeEach
    public void setUp(){
        this.integerSorter = new Sorter<>();

        this.arrEmpty = new ArrayList<>();
        this.arrOneElement = new ArrayList<>(Arrays.asList(1));
        this.arrIncreasing = new ArrayList<>(Arrays.asList(1, 3, 4, 5, 7, 8, 9));
        this.arrNonIncreasing = new ArrayList<>(Arrays.asList(9, 9, 6, 4, 4, 3, 1));
        this.arrDecreasing = new ArrayList<>(Arrays.asList(8, 7, 5, 4, 2, 1));
        this.arrNonDecreasing = new ArrayList<>(Arrays.asList(1, 1, 1, 3, 4, 5, 5, 6));
        this.arrRepeating = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1));
        this.arrUnorderedNoDuplicate = new ArrayList<>(Arrays.asList(9, 4, 1, 2, 5, 3, 6));
        this.arrUnorderedWithDuplicate = new ArrayList<>(Arrays.asList(4, 4, 8, 7, 1, 1, 2));
    }

    private void testAllExamplesAreSorted(){
        assertEquals(this.arrEmpty, new ArrayList<Integer>(Arrays.asList()));
        assertEquals(this.arrOneElement, new ArrayList<Integer>(Arrays.asList(1)));
        assertEquals(this.arrIncreasing, new ArrayList<Integer>(Arrays.asList(1, 3, 4, 5, 7, 8, 9)));
        assertEquals(this.arrNonIncreasing, new ArrayList<Integer>(Arrays.asList(1, 3, 4, 4, 6, 9, 9)));
        assertEquals(this.arrDecreasing, new ArrayList<Integer>(Arrays.asList(1, 2, 4, 5, 7, 8)));
        assertEquals(this.arrNonDecreasing, new ArrayList<Integer>(Arrays.asList(1, 1, 1, 3, 4, 5, 5, 6)));
        assertEquals(this.arrRepeating, new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1)));
        assertEquals(this.arrUnorderedNoDuplicate, new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 9)));
        assertEquals(this.arrUnorderedWithDuplicate, new ArrayList<Integer>(Arrays.asList(1, 1, 2, 4, 4, 7, 8)));
    }

    @Test
    @Order(1)
    public void testIsSorted(){
        assertTrue(this.integerSorter.isSorted(this.arrEmpty));
        assertTrue(this.integerSorter.isSorted(this.arrOneElement));
        assertTrue(this.integerSorter.isSorted(this.arrIncreasing));
        assertFalse(this.integerSorter.isSorted(this.arrNonIncreasing));
        assertFalse(this.integerSorter.isSorted(this.arrDecreasing));
        assertTrue(this.integerSorter.isSorted(this.arrNonDecreasing));
        assertTrue(this.integerSorter.isSorted(this.arrRepeating));
        assertFalse(this.integerSorter.isSorted(this.arrUnorderedNoDuplicate));
        assertFalse(this.integerSorter.isSorted(this.arrUnorderedWithDuplicate));
    }

    @Test
    public void testSelectionSort(){
        this.integerSorter.selectionSort(this.arrEmpty);
        this.integerSorter.selectionSort(this.arrOneElement);
        this.integerSorter.selectionSort(this.arrIncreasing);
        this.integerSorter.selectionSort(this.arrNonIncreasing);
        this.integerSorter.selectionSort(this.arrDecreasing);
        this.integerSorter.selectionSort(this.arrNonDecreasing);
        this.integerSorter.selectionSort(this.arrRepeating);
        this.integerSorter.selectionSort(this.arrUnorderedNoDuplicate);
        this.integerSorter.selectionSort(this.arrUnorderedWithDuplicate);

        this.testAllExamplesAreSorted();
    }

    @Test
    public void testInsertionSort(){
        this.integerSorter.insertionSort(this.arrEmpty);
        this.integerSorter.insertionSort(this.arrOneElement);
        this.integerSorter.insertionSort(this.arrIncreasing);
        this.integerSorter.insertionSort(this.arrNonIncreasing);
        this.integerSorter.insertionSort(this.arrDecreasing);
        this.integerSorter.insertionSort(this.arrNonDecreasing);
        this.integerSorter.insertionSort(this.arrRepeating);
        this.integerSorter.insertionSort(this.arrUnorderedNoDuplicate);
        this.integerSorter.insertionSort(this.arrUnorderedWithDuplicate);
        this.testAllExamplesAreSorted();
    }

    @Test
    public void testMergeSort(){
        this.integerSorter.mergeSort(this.arrEmpty);
        this.integerSorter.mergeSort(this.arrOneElement);
        this.integerSorter.mergeSort(this.arrIncreasing);
        this.integerSorter.mergeSort(this.arrNonIncreasing);
        this.integerSorter.mergeSort(this.arrDecreasing);
        this.integerSorter.mergeSort(this.arrNonDecreasing);
        this.integerSorter.mergeSort(this.arrRepeating);
        this.integerSorter.mergeSort(this.arrUnorderedNoDuplicate);
        this.integerSorter.mergeSort(this.arrUnorderedWithDuplicate);
        this.testAllExamplesAreSorted();
    }

    @Test
    public void testMergeSortBottomUp(){
        this.integerSorter.mergeSortBottomUp(this.arrEmpty);
        this.integerSorter.mergeSortBottomUp(this.arrOneElement);
        this.integerSorter.mergeSortBottomUp(this.arrIncreasing);
        this.integerSorter.mergeSortBottomUp(this.arrNonIncreasing);
        this.integerSorter.mergeSortBottomUp(this.arrDecreasing);
        this.integerSorter.mergeSortBottomUp(this.arrNonDecreasing);
        this.integerSorter.mergeSortBottomUp(this.arrRepeating);
        this.integerSorter.mergeSortBottomUp(this.arrUnorderedNoDuplicate);
        this.integerSorter.mergeSortBottomUp(this.arrUnorderedWithDuplicate);
        this.testAllExamplesAreSorted();
    }

    @Test
    public void testQuickSort(){
        this.integerSorter.quickSort(this.arrEmpty);
        this.integerSorter.quickSort(this.arrOneElement);
        this.integerSorter.quickSort(this.arrIncreasing);
        this.integerSorter.quickSort(this.arrNonIncreasing);
        this.integerSorter.quickSort(this.arrDecreasing);
        this.integerSorter.quickSort(this.arrNonDecreasing);
        this.integerSorter.quickSort(this.arrRepeating);
        this.integerSorter.quickSort(this.arrUnorderedNoDuplicate);
        this.integerSorter.quickSort(this.arrUnorderedWithDuplicate);
        this.testAllExamplesAreSorted();
    }

    @Test
    public void testHeapSort(){
        this.integerSorter.heapSort(this.arrEmpty);
        this.integerSorter.heapSort(this.arrOneElement);
        this.integerSorter.heapSort(this.arrIncreasing);
        this.integerSorter.heapSort(this.arrNonIncreasing);
        this.integerSorter.heapSort(this.arrDecreasing);
        this.integerSorter.heapSort(this.arrNonDecreasing);
        this.integerSorter.heapSort(this.arrRepeating);
        this.integerSorter.heapSort(this.arrUnorderedNoDuplicate);
        this.integerSorter.heapSort(this.arrUnorderedWithDuplicate);
        this.testAllExamplesAreSorted();
    }

    @Test
    public void testEfficiency(){
        ArrayList<Integer> arr1 = Generator.randomIntegerArray(10000);
        ArrayList<Integer> arr2 = new ArrayList<>(arr1);
        ArrayList<Integer> arr3 = new ArrayList<>(arr1);
        ArrayList<Integer> arr4 = Generator.randomIntegerArray(100000);
        ArrayList<Integer> arr5 = new ArrayList<>(arr4);
        ArrayList<Integer> arr6 = new ArrayList<>(arr4);
        ArrayList<Integer> arr7 = new ArrayList<>(arr4);
        ArrayList<Integer> nearlyOrdered1 = Generator.nearlyOrderedArray(10000);
        ArrayList<Integer> nearlyOrdered2 = new ArrayList<>(nearlyOrdered1);
        assertTimeout(Duration.ofMillis(10000), () -> {
            System.out.println("[Sorter] test sorting random integer array of size 10,000 "
                + "with selection sort");
            StopWatch.shared.begin();
            this.integerSorter.selectionSort(arr1);
            StopWatch.shared.end("Runtime:", 0.5);
            assertTrue(this.integerSorter.isSorted(arr1));
        });

        assertTimeout(Duration.ofMillis(10000), () -> {
            System.out.println("[Sorter] test sorting random integer array of size 10,000 "
                + "with insertion sort");
            StopWatch.shared.begin();
            this.integerSorter.insertionSort(arr2);
            StopWatch.shared.end("Runtime:", 0.5);
            assertTrue(this.integerSorter.isSorted(arr2));
        });

        assertTimeout(Duration.ofMillis(10000), () -> {
            System.out.println("[Sorter] test sorting nearly ordered integer array of size "
                + "10,000 with selection sort");

            StopWatch.shared.begin();
            this.integerSorter.selectionSort(nearlyOrdered1);
            StopWatch.shared.end("Runtime:", 0.5);
            assertTrue(this.integerSorter.isSorted(nearlyOrdered1));
        });

        assertTimeout(Duration.ofMillis(10000), () -> {
            System.out.println("[Sorter] test sorting nearly ordered integer array of size "
                + "10,000 with insertion sort");

            StopWatch.shared.begin();
            this.integerSorter.insertionSort(nearlyOrdered2);
            StopWatch.shared.end("Runtime:", 0.5);
            assertTrue(this.integerSorter.isSorted(nearlyOrdered2));
        });

        assertTimeout(Duration.ofMillis(10000), () -> {
            System.out.println("[Sorter] test sorting random integer array of size 10,000 "
                + "with merge sort");
            StopWatch.shared.begin();
            this.integerSorter.mergeSort(arr3);
            StopWatch.shared.end("Runtime:", 0.1);
            assertTrue(this.integerSorter.isSorted(arr3));
        });

        assertTimeout(Duration.ofMillis(10000), () -> {
            System.out.println("[Sorter] test sorting random integer array of size 100,000 "
                + "with merge sort");
            StopWatch.shared.begin();
            this.integerSorter.mergeSort(arr4);
            StopWatch.shared.end("Runtime:", 0.25);
            assertTrue(this.integerSorter.isSorted(arr4));
        });

        assertTimeout(Duration.ofMillis(10000), () -> {
            System.out.println("[Sorter] test sorting random integer array of size 100,000 "
                + "with merge sort bottom-up");
            StopWatch.shared.begin();
            this.integerSorter.mergeSortBottomUp(arr5);
            StopWatch.shared.end("Runtime:", 0.25);
            assertTrue(this.integerSorter.isSorted(arr5));
        });

        assertTimeout(Duration.ofMillis(10000), () -> {
            System.out.println("[Sorter] test sorting random integer array of size 100,000 "
                + "with quick sort");
            StopWatch.shared.begin();
            this.integerSorter.quickSort(arr6);
            StopWatch.shared.end("Runtime:", 0.25);
            assertTrue(this.integerSorter.isSorted(arr6));
        });

        assertTimeout(Duration.ofMillis(10000), () -> {
            System.out.println("[Sorter] test sorting random integer array of size 100,000 "
                + "with heap sort");
            StopWatch.shared.begin();
            this.integerSorter.heapSort(arr7);
            StopWatch.shared.end("Runtime:", 0.25);
            assertTrue(this.integerSorter.isSorted(arr7));
        });
    }
}