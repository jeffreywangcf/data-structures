package datastructures.algo;

import datastructures.util.Generator;
import datastructures.util.StopWatch;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SortTest{

    private Sort<Integer> integerSort;

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
        this.integerSort = new Sort<>();

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
        assertTrue(this.integerSort.isSorted(this.arrEmpty));
        assertTrue(this.integerSort.isSorted(this.arrOneElement));
        assertTrue(this.integerSort.isSorted(this.arrIncreasing));
        assertFalse(this.integerSort.isSorted(this.arrNonIncreasing));
        assertFalse(this.integerSort.isSorted(this.arrDecreasing));
        assertTrue(this.integerSort.isSorted(this.arrNonDecreasing));
        assertTrue(this.integerSort.isSorted(this.arrRepeating));
        assertFalse(this.integerSort.isSorted(this.arrUnorderedNoDuplicate));
        assertFalse(this.integerSort.isSorted(this.arrUnorderedWithDuplicate));
    }

    @Test
    public void testSelectionSort(){
        this.integerSort.selectionSort(this.arrEmpty);
        this.integerSort.selectionSort(this.arrOneElement);
        this.integerSort.selectionSort(this.arrIncreasing);
        this.integerSort.selectionSort(this.arrNonIncreasing);
        this.integerSort.selectionSort(this.arrDecreasing);
        this.integerSort.selectionSort(this.arrNonDecreasing);
        this.integerSort.selectionSort(this.arrRepeating);
        this.integerSort.selectionSort(this.arrUnorderedNoDuplicate);
        this.integerSort.selectionSort(this.arrUnorderedWithDuplicate);

        this.testAllExamplesAreSorted();
    }

    @Test
    public void testInsertionSort(){
        this.integerSort.insertionSort(this.arrEmpty);
        this.integerSort.insertionSort(this.arrOneElement);
        this.integerSort.insertionSort(this.arrIncreasing);
        this.integerSort.insertionSort(this.arrNonIncreasing);
        this.integerSort.insertionSort(this.arrDecreasing);
        this.integerSort.insertionSort(this.arrNonDecreasing);
        this.integerSort.insertionSort(this.arrRepeating);
        this.integerSort.insertionSort(this.arrUnorderedNoDuplicate);
        this.integerSort.insertionSort(this.arrUnorderedWithDuplicate);
        this.testAllExamplesAreSorted();
    }

    @Test
    public void testMergeSort(){
        this.integerSort.mergeSort(this.arrEmpty);
        this.integerSort.mergeSort(this.arrOneElement);
        this.integerSort.mergeSort(this.arrIncreasing);
        this.integerSort.mergeSort(this.arrNonIncreasing);
        this.integerSort.mergeSort(this.arrDecreasing);
        this.integerSort.mergeSort(this.arrNonDecreasing);
        this.integerSort.mergeSort(this.arrRepeating);
        this.integerSort.mergeSort(this.arrUnorderedNoDuplicate);
        this.integerSort.mergeSort(this.arrUnorderedWithDuplicate);
        this.testAllExamplesAreSorted();
    }

    @Test
    public void testMergeSortBottomUp(){
        this.integerSort.mergeSortBottomUp(this.arrEmpty);
        this.integerSort.mergeSortBottomUp(this.arrOneElement);
        this.integerSort.mergeSortBottomUp(this.arrIncreasing);
        this.integerSort.mergeSortBottomUp(this.arrNonIncreasing);
        this.integerSort.mergeSortBottomUp(this.arrDecreasing);
        this.integerSort.mergeSortBottomUp(this.arrNonDecreasing);
        this.integerSort.mergeSortBottomUp(this.arrRepeating);
        this.integerSort.mergeSortBottomUp(this.arrUnorderedNoDuplicate);
        this.integerSort.mergeSortBottomUp(this.arrUnorderedWithDuplicate);
        this.testAllExamplesAreSorted();
    }

    @Test
    public void testQuickSort(){
        this.integerSort.quickSort(this.arrEmpty);
        this.integerSort.quickSort(this.arrOneElement);
        this.integerSort.quickSort(this.arrIncreasing);
        this.integerSort.quickSort(this.arrNonIncreasing);
        this.integerSort.quickSort(this.arrDecreasing);
        this.integerSort.quickSort(this.arrNonDecreasing);
        this.integerSort.quickSort(this.arrRepeating);
        this.integerSort.quickSort(this.arrUnorderedNoDuplicate);
        this.integerSort.quickSort(this.arrUnorderedWithDuplicate);
        this.testAllExamplesAreSorted();
    }

    @Test
    public void testHeapSort(){
        this.integerSort.heapSort(this.arrEmpty);
        this.integerSort.heapSort(this.arrOneElement);
        this.integerSort.heapSort(this.arrIncreasing);
        this.integerSort.heapSort(this.arrNonIncreasing);
        this.integerSort.heapSort(this.arrDecreasing);
        this.integerSort.heapSort(this.arrNonDecreasing);
        this.integerSort.heapSort(this.arrRepeating);
        this.integerSort.heapSort(this.arrUnorderedNoDuplicate);
        this.integerSort.heapSort(this.arrUnorderedWithDuplicate);
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
            this.integerSort.selectionSort(arr1);
            StopWatch.shared.end("Runtime:", 0.5);
            assertTrue(this.integerSort.isSorted(arr1));
        });

        assertTimeout(Duration.ofMillis(10000), () -> {
            System.out.println("[Sorter] test sorting random integer array of size 10,000 "
                    + "with insertion sort");
            StopWatch.shared.begin();
            this.integerSort.insertionSort(arr2);
            StopWatch.shared.end("Runtime:", 0.5);
            assertTrue(this.integerSort.isSorted(arr2));
        });

        assertTimeout(Duration.ofMillis(10000), () -> {
            System.out.println("[Sorter] test sorting nearly ordered integer array of size "
                    + "10,000 with selection sort");

            StopWatch.shared.begin();
            this.integerSort.selectionSort(nearlyOrdered1);
            StopWatch.shared.end("Runtime:", 0.5);
            assertTrue(this.integerSort.isSorted(nearlyOrdered1));
        });

        assertTimeout(Duration.ofMillis(10000), () -> {
            System.out.println("[Sorter] test sorting nearly ordered integer array of size "
                    + "10,000 with insertion sort");

            StopWatch.shared.begin();
            this.integerSort.insertionSort(nearlyOrdered2);
            StopWatch.shared.end("Runtime:", 0.5);
            assertTrue(this.integerSort.isSorted(nearlyOrdered2));
        });

        assertTimeout(Duration.ofMillis(10000), () -> {
            System.out.println("[Sorter] test sorting random integer array of size 10,000 "
                    + "with merge sort");
            StopWatch.shared.begin();
            this.integerSort.mergeSort(arr3);
            StopWatch.shared.end("Runtime:", 0.1);
            assertTrue(this.integerSort.isSorted(arr3));
        });

        assertTimeout(Duration.ofMillis(10000), () -> {
            System.out.println("[Sorter] test sorting random integer array of size 100,000 "
                    + "with merge sort");
            StopWatch.shared.begin();
            this.integerSort.mergeSort(arr4);
            StopWatch.shared.end("Runtime:", 0.25);
            assertTrue(this.integerSort.isSorted(arr4));
        });

        assertTimeout(Duration.ofMillis(10000), () -> {
            System.out.println("[Sorter] test sorting random integer array of size 100,000 "
                    + "with merge sort bottom-up");
            StopWatch.shared.begin();
            this.integerSort.mergeSortBottomUp(arr5);
            StopWatch.shared.end("Runtime:", 0.25);
            assertTrue(this.integerSort.isSorted(arr5));
        });

        assertTimeout(Duration.ofMillis(10000), () -> {
            System.out.println("[Sorter] test sorting random integer array of size 100,000 "
                    + "with quick sort");
            StopWatch.shared.begin();
            this.integerSort.quickSort(arr6);
            StopWatch.shared.end("Runtime:", 0.25);
            assertTrue(this.integerSort.isSorted(arr6));
        });

        assertTimeout(Duration.ofMillis(10000), () -> {
            System.out.println("[Sorter] test sorting random integer array of size 100,000 "
                    + "with heap sort");
            StopWatch.shared.begin();
            this.integerSort.heapSort(arr7);
            StopWatch.shared.end("Runtime:", 0.25);
            assertTrue(this.integerSort.isSorted(arr7));
        });
    }
}