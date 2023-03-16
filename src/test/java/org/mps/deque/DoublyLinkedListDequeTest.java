package org.mps.deque;

import org.junit.jupiter.api.*;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
/**
 * @author brisanx (Alba Sanchez Ibanez) Fernando Calvo Diaz
 *
 */
@DisplayName("Tests results for the DoublyLinkedListDeque")
class DoublyLinkedListDequeTest {

    @Nested
    @DisplayName("when a DoublyLinkedListDeque is empty")
    class WhenIsEmpty {
        private DoublyLinkedListDeque<Integer> deque;

        @BeforeEach
        void setUp() {
            deque = new DoublyLinkedListDeque<>();
        }

        @Test
        @DisplayName("the size should be 0")
        void sizeShouldBeZero() {
            int expectedValue = 0;
            int actualValue = deque.size();
            assertEquals(expectedValue, actualValue);
        }

        @Test
        @DisplayName("and delete first element should throw DoubleEndedQueueException")
        void deleteFirstShouldThrowException() {
            assertThrows(DoubleEndedQueueException.class, () -> deque.deleteFirst());
        }

        @Test
        @DisplayName("and delete last element should throw DoubleEndedQueueException")
        void deleteLastShouldThrowException() {
            assertThrows(DoubleEndedQueueException.class, () -> deque.deleteLast());
        }
        @Test
        @DisplayName("and wants to know first element should throw DoubleEndedQueueException")
        void firstElementShouldThrowException(){
            assertThrows(DoubleEndedQueueException.class, () -> deque.first());
        }

        @Test
        @DisplayName("and wants to know last element should throw DoubleEndedQueueException")
        void lastElementShouldThrowException(){
            assertThrows(DoubleEndedQueueException.class, () -> deque.last());
        }

        @Test
        @DisplayName("and wants to insert first element should be added at the beginning")
        void insertFirstAtBeginning() {
            deque.prepend(1);
            int expectedValueSize = 1;
            int actualValueSize = deque.size();
            int actualValueFirst = deque.first();
            assertEquals(expectedValueSize, actualValueSize);
            assertEquals(1, actualValueFirst);
            assertThat(deque.first(), is(notNullValue()));
        }

        @Test
        @DisplayName("and wants to insert first element should be added at the end")
        void insertLastShouldAddElementAtEnd() {
            deque.append(1);
            int expectedValueSize = 1;
            int actualValueSize = deque.size();
            int actualValueLast = deque.last();
            assertEquals(expectedValueSize, actualValueSize);
            assertEquals(1, actualValueLast);
            assertThat(deque.last(), is(notNullValue()));
        }

        @Test
        @DisplayName("and wants to get any position in an empty list")
        void getPositionShouldThrowException(){
            assertThrows(IndexOutOfBoundsException.class, ()->deque.get(0));
        }

        @Test
        @DisplayName("sort throws DoublyLinkedListException")
        void sortShouldThrowException(){
            assertThrows(DoubleEndedQueueException.class, ()->deque.sort(Collections.reverseOrder()));
        }
    }

    @Nested
    @DisplayName("when a DoublyLinkedListDeque is not empty")
    class WhenIsNotEmpty {

        private DoublyLinkedListDeque<Integer> deque;

        @BeforeEach
        void setUp() {
            deque = new DoublyLinkedListDeque<>();
            deque.append(1);
            deque.append(2);
            deque.append(3);
        }

        @Test
        @DisplayName("size should be greater than 0")
        void sizeShouldBeGreaterThanZero() {
            assertThat(deque.size(), greaterThan(0));
        }

        @Test
        @DisplayName("first should return the first element")
        void firstShouldReturnFirstElement() {
            assertEquals(1, deque.first());
        }

        @Test
        @DisplayName("last should return the last element")
        void lastShouldReturnLastElement() {
            assertEquals(3, deque.last());
        }

        @Test
        @DisplayName("delete first element should remove the first element")
        void deleteFirstShouldRemoveFirstElement() {
            deque.deleteFirst();
            int expectedValue = 2;
            int actualValueSize = deque.size();
            int actualValueFirst = deque.first();
            assertEquals(expectedValue, actualValueSize);
            assertEquals(expectedValue, actualValueFirst);
        }

        @Test
        @DisplayName("delete last element should remove the last element")
        void deleteLastShouldRemoveLastElement() {
            deque.deleteLast();
            int expectedValue = 2;
            int actualValueSize = deque.size();
            int actualValueLast = deque.last();
            assertEquals(expectedValue, actualValueSize);
            assertEquals(expectedValue, actualValueLast);
        }

        @Test
        @DisplayName("prepend should add an element at the beginning")
        void prependShouldAddElementAtBeginning() {
            int expectedValueSize = 4;
            deque.prepend(0);
            assertEquals(expectedValueSize, deque.size());
            assertEquals(0, deque.first());
        }

        @Test
        @DisplayName("append should add an element at the end")
        void appendShouldAddElementAtEnd() {
            int expectedValue = 4;
            deque.append(4);
            int actualValueSize = deque.size();
            int actualValueLast = deque.last();
            assertEquals(expectedValue, actualValueSize);
            assertEquals(expectedValue, actualValueLast);
        }

        @Test
        @DisplayName("delete all elements being the last element deleted by the method deleteLat()")
        void deleteLastElementByDeleteLast() {
            deque.deleteLast();
            deque.deleteLast();
            deque.deleteLast();

            int expectedSizeValue = 0;
            int actualValue = deque.size();
            assertEquals(expectedSizeValue, actualValue);
            assertThrows(DoubleEndedQueueException.class, () -> deque.first());
            assertThrows(DoubleEndedQueueException.class, () -> deque.last());
        }

        @Test
        @DisplayName("delete all elements being the last element deleted by the method deleteFirst()")
        void deleteLastElementByDeleteFirst() {
            deque.deleteFirst();
            deque.deleteFirst();
            deque.deleteFirst();

            int expectedSizeValue = 0;
            int actualValue = deque.size();
            assertEquals(expectedSizeValue, actualValue);
            assertThrows(DoubleEndedQueueException.class, () -> deque.first());
            assertThrows(DoubleEndedQueueException.class, () -> deque.last());
        }

        @Test
        @DisplayName("and wants to know the first element")
        void getFirstElementReturnsFirstElement(){
            int expectedValue = 1;
            int actualValue = deque.get(0);
            assertEquals(actualValue, expectedValue);
        }

        @Test
        @DisplayName("and wants to know the last element")
        void getLastElementReturnsFirstElement(){
            int expectedValue = 3;
            int actualValue = deque.get(deque.size()-1);
            assertEquals(actualValue, expectedValue);
        }
        @Test
        @DisplayName("and wants to know any element in the middle")
        void getAnyElementInTheMiddleReturnsFirstElement(){
            int expectedValue = 2;
            int actualValue = deque.get(1);
            assertEquals(actualValue, expectedValue);
        }

        @Test
        @DisplayName("and wants to get an element with an index out of range")
        void testGetIndexOutOfRange() {
            assertThrows(IndexOutOfBoundsException.class, () -> deque.get(3));
        }

        @Test
        @DisplayName("Value is contained")
        void testContainsValue() {
            assertThat(deque.contains(2), is(true));
        }

        @Test
        @DisplayName("Value is not contained")
        void testContainsValueNotContained() {
            assertThat(deque.contains(4), is(false));
        }

        @Test
        @DisplayName("and wants to remove the first element")
        void removeFirstElementR(){
            deque.remove(1);
            deque.append(1);
            deque.remove(1);
            assertThat(deque.contains(1), is(false));

            int expectedValue = 2;
            int firstElement = deque.first();
            assertEquals(expectedValue, firstElement);
        }

        @Test
        @DisplayName("and wants to remove the last element")
        void removeLastElement(){
            int expectedValue = 3;
            int actualValue = deque.get(deque.size()-1);
            assertEquals(actualValue, expectedValue);
        }

        @Test
        @DisplayName("no me acuerdo de cual era este xd")
        void removeElementInTheMiddleOfADequeOfThree(){
            int expectedValue = 2;
            int actualValue = deque.get(deque.size()-2);
            assertEquals(actualValue, expectedValue);
        }

        @Test
        @DisplayName("sort should sort")
        void sortListShouldReturnSortedList(){
            deque.append(9);
            deque.append(5);
            deque.append(8);
            deque.append(6);
        }
    }
}