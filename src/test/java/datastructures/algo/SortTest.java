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
        assertTrue(Sort.isSorted(this.arrEmpty));
        assertTrue(Sort.isSorted(this.arrOneElement));
        assertTrue(Sort.isSorted(this.arrIncreasing));
        assertFalse(Sort.isSorted(this.arrNonIncreasing));
        assertFalse(Sort.isSorted(this.arrDecreasing));
        assertTrue(Sort.isSorted(this.arrNonDecreasing));
        assertTrue(Sort.isSorted(this.arrRepeating));
        assertFalse(Sort.isSorted(this.arrUnorderedNoDuplicate));
        assertFalse(Sort.isSorted(this.arrUnorderedWithDuplicate));
    }

    @Test
    public void testSelectionSort(){
        Sort.selectionSort(this.arrEmpty);
        Sort.selectionSort(this.arrOneElement);
        Sort.selectionSort(this.arrIncreasing);
        Sort.selectionSort(this.arrNonIncreasing);
        Sort.selectionSort(this.arrDecreasing);
        Sort.selectionSort(this.arrNonDecreasing);
        Sort.selectionSort(this.arrRepeating);
        Sort.selectionSort(this.arrUnorderedNoDuplicate);
        Sort.selectionSort(this.arrUnorderedWithDuplicate);

        this.testAllExamplesAreSorted();
    }

    @Test
    public void testInsertionSort(){
        Sort.insertionSort(this.arrEmpty);
        Sort.insertionSort(this.arrOneElement);
        Sort.insertionSort(this.arrIncreasing);
        Sort.insertionSort(this.arrNonIncreasing);
        Sort.insertionSort(this.arrDecreasing);
        Sort.insertionSort(this.arrNonDecreasing);
        Sort.insertionSort(this.arrRepeating);
        Sort.insertionSort(this.arrUnorderedNoDuplicate);
        Sort.insertionSort(this.arrUnorderedWithDuplicate);
        this.testAllExamplesAreSorted();
    }

    @Test
    public void testMergeSort(){
        Sort.mergeSort(this.arrEmpty);
        Sort.mergeSort(this.arrOneElement);
        Sort.mergeSort(this.arrIncreasing);
        Sort.mergeSort(this.arrNonIncreasing);
        Sort.mergeSort(this.arrDecreasing);
        Sort.mergeSort(this.arrNonDecreasing);
        Sort.mergeSort(this.arrRepeating);
        Sort.mergeSort(this.arrUnorderedNoDuplicate);
        Sort.mergeSort(this.arrUnorderedWithDuplicate);
        this.testAllExamplesAreSorted();
    }

    @Test
    public void testMergeSortBottomUp(){
        Sort.mergeSortBottomUp(this.arrEmpty);
        Sort.mergeSortBottomUp(this.arrOneElement);
        Sort.mergeSortBottomUp(this.arrIncreasing);
        Sort.mergeSortBottomUp(this.arrNonIncreasing);
        Sort.mergeSortBottomUp(this.arrDecreasing);
        Sort.mergeSortBottomUp(this.arrNonDecreasing);
        Sort.mergeSortBottomUp(this.arrRepeating);
        Sort.mergeSortBottomUp(this.arrUnorderedNoDuplicate);
        Sort.mergeSortBottomUp(this.arrUnorderedWithDuplicate);
        this.testAllExamplesAreSorted();
    }

    @Test
    public void testQuickSort(){
        Sort.quickSort(this.arrEmpty);
        Sort.quickSort(this.arrOneElement);
        Sort.quickSort(this.arrIncreasing);
        Sort.quickSort(this.arrNonIncreasing);
        Sort.quickSort(this.arrDecreasing);
        Sort.quickSort(this.arrNonDecreasing);
        Sort.quickSort(this.arrRepeating);
        Sort.quickSort(this.arrUnorderedNoDuplicate);
        Sort.quickSort(this.arrUnorderedWithDuplicate);
        this.testAllExamplesAreSorted();
    }

    @Test
    public void testHeapSort(){
        Sort.heapSort(this.arrEmpty);
        Sort.heapSort(this.arrOneElement);
        Sort.heapSort(this.arrIncreasing);
        Sort.heapSort(this.arrNonIncreasing);
        Sort.heapSort(this.arrDecreasing);
        Sort.heapSort(this.arrNonDecreasing);
        Sort.heapSort(this.arrRepeating);
        Sort.heapSort(this.arrUnorderedNoDuplicate);
        Sort.heapSort(this.arrUnorderedWithDuplicate);
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
            Sort.selectionSort(arr1);
            StopWatch.shared.end("Runtime:", 0.5);
            assertTrue(Sort.isSorted(arr1));
        });

        assertTimeout(Duration.ofMillis(10000), () -> {
            System.out.println("[Sorter] test sorting random integer array of size 10,000 "
                    + "with insertion sort");
            StopWatch.shared.begin();
            Sort.insertionSort(arr2);
            StopWatch.shared.end("Runtime:", 0.5);
            assertTrue(Sort.isSorted(arr2));
        });

        assertTimeout(Duration.ofMillis(10000), () -> {
            System.out.println("[Sorter] test sorting nearly ordered integer array of size "
                    + "10,000 with selection sort");

            StopWatch.shared.begin();
            Sort.selectionSort(nearlyOrdered1);
            StopWatch.shared.end("Runtime:", 0.5);
            assertTrue(Sort.isSorted(nearlyOrdered1));
        });

        assertTimeout(Duration.ofMillis(10000), () -> {
            System.out.println("[Sorter] test sorting nearly ordered integer array of size "
                    + "10,000 with insertion sort");

            StopWatch.shared.begin();
            Sort.insertionSort(nearlyOrdered2);
            StopWatch.shared.end("Runtime:", 0.5);
            assertTrue(Sort.isSorted(nearlyOrdered2));
        });

        assertTimeout(Duration.ofMillis(10000), () -> {
            System.out.println("[Sorter] test sorting random integer array of size 10,000 "
                    + "with merge sort");
            StopWatch.shared.begin();
            Sort.mergeSort(arr3);
            StopWatch.shared.end("Runtime:", 0.1);
            assertTrue(Sort.isSorted(arr3));
        });

        assertTimeout(Duration.ofMillis(10000), () -> {
            System.out.println("[Sorter] test sorting random integer array of size 100,000 "
                    + "with merge sort");
            StopWatch.shared.begin();
            Sort.mergeSort(arr4);
            StopWatch.shared.end("Runtime:", 0.25);
            assertTrue(Sort.isSorted(arr4));
        });

        assertTimeout(Duration.ofMillis(10000), () -> {
            System.out.println("[Sorter] test sorting random integer array of size 100,000 "
                    + "with merge sort bottom-up");
            StopWatch.shared.begin();
            Sort.mergeSortBottomUp(arr5);
            StopWatch.shared.end("Runtime:", 0.25);
            assertTrue(Sort.isSorted(arr5));
        });

        assertTimeout(Duration.ofMillis(10000), () -> {
            System.out.println("[Sorter] test sorting random integer array of size 100,000 "
                    + "with quick sort");
            StopWatch.shared.begin();
            Sort.quickSort(arr6);
            StopWatch.shared.end("Runtime:", 0.25);
            assertTrue(Sort.isSorted(arr6));
        });

        assertTimeout(Duration.ofMillis(10000), () -> {
            System.out.println("[Sorter] test sorting random integer array of size 100,000 "
                    + "with heap sort");
            StopWatch.shared.begin();
            Sort.heapSort(arr7);
            StopWatch.shared.end("Runtime:", 0.25);
            assertTrue(Sort.isSorted(arr7));
        });
    }
}