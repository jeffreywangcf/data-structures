package datastructures.ds;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DoublyEndedQueueTest{

    private DoublyEndedQueue<Integer> mtDeq;
    private DoublyEndedQueue<Integer> deq1;

    @BeforeEach
    public void setUp(){
        this.mtDeq = new DoublyEndedQueue<>();
        this.deq1 = new DoublyEndedQueue<>(List.of(1, 2, 3));
    }

    /**
     * verify the given DEQ has the correct value and correctly link its prev and next according to elements
     * @param deq DEQ to test on
     * @param elements expected order of elements
     * @param <T> type of DEQ and elements
     */
    private <T> void verify(DoublyEndedQueue<T> deq, List<T> elements){
        assertEquals(deq.size(), elements.size());
        if(elements.isEmpty()){
            return;
        }
        DoublyEndedQueue.Node<T> head = deq.head;
        DoublyEndedQueue.Node<T> current = deq.head;
        assertEquals(current.getValue(), elements.get(0));
        if(elements.size() == 1){
            assertEquals(current.getNext().getValue(), elements.get(0));
            assertEquals(current.getPrev().getValue(), elements.get(0));
        }else{
            assertEquals(current.getNext().getValue(), elements.get(1));
            assertEquals(current.getPrev().getValue(), elements.get(elements.size() - 1));
        }
        current = current.getNext();
        for(int i = 1; i < elements.size() - 1; i++){
            assertEquals(current.getValue(), elements.get(i));
            assertEquals(current.getNext().getValue(), elements.get(i + 1));
            assertEquals(current.getPrev().getValue(), elements.get(i - 1));
            current = current.getNext();
        }
        if(elements.size() > 1){
            assertEquals(current.getValue(), elements.get(elements.size() - 1));
            assertEquals(current.getNext().getValue(), elements.get(0));
            assertEquals(current.getPrev().getValue(), elements.get(elements.size() - 2));
            assertEquals(current.getNext(), head);
        }
    }

    @Test
    public void testConstructor(){
        assertNull(this.mtDeq.head);
        assertNotNull(this.deq1.head);
        this.verify(this.deq1, List.of(1, 2, 3));
        this.verify(new DoublyEndedQueue<>(List.of(1)), List.of(1));
    }

    @Test
    public void testIterator(){
        Iterator<Integer> iter = this.mtDeq.iterator();
        assertFalse(iter.hasNext());
        iter = this.deq1.iterator();
        for(int i = 0; i < 3; i++){
            assertTrue(iter.hasNext());
            assertEquals(iter.next(), i + 1);
        }
        assertFalse(iter.hasNext());
        iter = this.deq1.reverseIterator();
        for(int i = 3; i > 0; i--){
            assertTrue(iter.hasNext());
            assertEquals(iter.next(), i);
        }
        assertFalse(iter.hasNext());
        this.mtDeq.insertFirst(1);
        iter = this.mtDeq.iterator();
        assertTrue(iter.hasNext());
        assertEquals(iter.next(), 1);
        assertFalse(iter.hasNext());
        iter = this.mtDeq.reverseIterator();
        assertTrue(iter.hasNext());
        assertEquals(iter.next(), 1);
        assertFalse(iter.hasNext());
    }

    @Test
    public void testInsert(){
        this.mtDeq.insertFirst(1);
        this.deq1.insertFirst(0);
        this.verify(this.mtDeq, List.of(1));
        this.verify(this.deq1, List.of(0, 1, 2, 3));
        this.deq1.insertFirst(-1);
        this.verify(this.deq1, List.of(-1, 0, 1, 2, 3));
        this.setUp();
        this.mtDeq.insertLast(1);
        this.deq1.insertLast(4);
        this.verify(this.mtDeq, List.of(1));
        this.verify(this.deq1, List.of(1, 2, 3, 4));
        this.deq1.insertLast(5);
        this.verify(this.deq1, List.of(1, 2, 3, 4, 5));
    }

    @Test
    public void testPeek(){
        assertThrows(IndexOutOfBoundsException.class, () -> {
            this.mtDeq.peekFirst();
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            this.mtDeq.peekLast();
        });
        assertEquals(this.deq1.peekFirst(), 1);
        assertEquals(this.deq1.peekLast(), 3);
        assertEquals(this.deq1.size(), 3);
    }

    @Test
    public void testSize(){
        assertEquals(this.mtDeq.size(), 0);
        assertEquals(this.deq1.size(), 3);
        this.deq1.insertLast(3);
        this.deq1.insertFirst(4);
        assertEquals(this.deq1.size(), 5);
    }

    @Test
    public void testIsEmpty(){
        assertTrue(this.mtDeq.isEmpty());
        assertFalse(this.deq1.isEmpty());
    }

}
