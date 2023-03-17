package org.mps.deque;

import org.junit.jupiter.api.*;

import java.util.Collections;
import java.util.Comparator;

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
    @DisplayName("when it is empty")
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
        @DisplayName("and wants to remove an element")
        void removeInEmptyList(){
            assertThat(deque.contains(2),is(false));
            deque.remove(2);
            assertThat(deque.contains(2),is(false));

        }
        @Nested
        @DisplayName("and wants to delete")
        class deleteEmpty {
            @Test
            @DisplayName("the first element should throw DoubleEndedQueueException")
            void deleteFirstShouldThrowException() {
                assertThrows(DoubleEndedQueueException.class, () -> deque.deleteFirst());
            }

            @Test
            @DisplayName("the last element should throw DoubleEndedQueueException")
            void deleteLastShouldThrowException() {
                assertThrows(DoubleEndedQueueException.class, () -> deque.deleteLast());
            }
        }

        @Nested
        @DisplayName("and wants to know")
        class positionsEmpty {
            @Test
            @DisplayName("the first element should throw DoubleEndedQueueException")
            void firstElementShouldThrowException(){
                assertThrows(DoubleEndedQueueException.class, () -> deque.first());
            }

            @Test
            @DisplayName("the last element should throw DoubleEndedQueueException")
            void lastElementShouldThrowException(){
                assertThrows(DoubleEndedQueueException.class, () -> deque.last());
            }
        }

        @Nested
        @DisplayName("and wants to insert")
        class addElementsInEmptyList {
            @Test
            @DisplayName("an element using prepend should be added being the unique element (first and last)")
            void insertFirstUsingPrepend() {
                deque.prepend(1);
                int expectedValue = 1;
                int actualValueSize = deque.size();
                int actualValueFirst = deque.first();
                int actualValueLast = deque.last();

                assertEquals(expectedValue, actualValueSize);
                assertEquals(expectedValue, actualValueFirst);
                assertEquals(expectedValue, actualValueLast);
                assertThat(deque.first(), is(notNullValue()));
                assertThat(deque.last(), is(notNullValue()));
            }

            @Test
            @DisplayName("an element using append should be added being the unique element (first and last)")
            void insertFirstUsingAppend() {
                deque.append(1);
                int expectedValue = 1;
                int actualValueSize = deque.size();
                int actualValueFirst = deque.first();
                int actualValueLast = deque.last();

                assertEquals(expectedValue, actualValueSize);
                assertEquals(expectedValue, actualValueFirst);
                assertEquals(expectedValue, actualValueLast);
                assertThat(deque.first(), is(notNullValue()));
                assertThat(deque.last(), is(notNullValue()));
            }
        }


        @Test
        @DisplayName("and wants to get any position should throws DoubleEndedQueueException")
        void getPositionShouldThrowException(){
            assertThrows(IndexOutOfBoundsException.class, ()->deque.get(0));
        }

        @Test
        @DisplayName("and wants to know the element in any position should throws DoubleEndedQueueException")
        public void containsInEmptyListAlwaysIsFalse() {
            assertThat(deque.contains(1), is(false));
        }

        @Test
        @DisplayName("and wants to sort should throws DoubleEndedQueueException")
        void sortShouldThrowException(){
            assertThrows(DoubleEndedQueueException.class, ()->deque.sort(Comparator.naturalOrder()));
        }
    }

    @Nested
    @DisplayName("when it is not empty")
    class WhenIsNotEmpty {

        private DoublyLinkedListDeque<Integer> deque;

        @BeforeEach
        void setUp() {
            deque = new DoublyLinkedListDeque<>();
            deque.append(1);
            deque.append(2);
            deque.append(3);
        }

        @Nested
        @DisplayName("and wants to know the position")
        class positions {

            @Test
            @DisplayName("first that should return the first element")
            void firstShouldReturnFirstElement() {
                assertEquals(1, deque.first());
            }

            @Test
            @DisplayName("last that should return the last element")
            void lastShouldReturnLastElement() {
                int expectedValue = 3;

                assertEquals(expectedValue, deque.last());
            }
        }

        @Test
        @DisplayName("size should be greater than 0")
        void sizeShouldBeGreaterThanZero() {
            assertThat(deque.size(), greaterThan(0));
        }

        @Nested
        @DisplayName("and wants to delete")
        class delete {
            @Test
            @DisplayName("first element should remove the first element")
            void deleteFirstShouldRemoveFirstElement() {
                deque.deleteFirst();
                int expectedValue = 2;
                int actualValueSize = deque.size();
                int actualValueFirst = deque.first();
                assertEquals(expectedValue, actualValueSize);
                assertEquals(expectedValue, actualValueFirst);
            }

            @Test
            @DisplayName("last element should remove the last element")
            void deleteLastShouldRemoveLastElement() {
                deque.deleteLast();
                int expectedValue = 2;
                int actualValueSize = deque.size();
                int actualValueLast = deque.last();
                assertEquals(expectedValue, actualValueSize);
                assertEquals(expectedValue, actualValueLast);
            }

            @Test
            @DisplayName("all elements being the last element deleted by the method deleteLast()")
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
            @DisplayName("all elements being the last element deleted by the method deleteFirst()")
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
        }

        @Nested
        @DisplayName("and wants to add using ")
        class prependAppend {
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
        }

        @Nested
        @DisplayName("and wants to know using get()")
        class get {
            @Test
            @DisplayName("the first element")
            void getFirstElementReturnsFirstElement(){
                int expectedValue = 1;
                int actualValue = deque.get(0);
                assertEquals(expectedValue, actualValue);
            }

            @Test
            @DisplayName("the last element")
            void getLastElementReturnsFirstElement(){
                int expectedValue = 3;
                int actualValue = deque.get(deque.size()-1);
                assertEquals(expectedValue,actualValue);
            }

            @Test
            @DisplayName("any element in the middle")
            void getAnyElementInTheMiddleReturnsFirstElement(){
                int expectedValue = 2;
                int actualValue = deque.get(1);
                assertEquals(expectedValue, actualValue);
            }

            @Test
            @DisplayName("an element with an index out of range")
            void testGetIndexOutOfRange() {
                assertThrows(IndexOutOfBoundsException.class, () -> deque.get(3));
                assertThrows(IndexOutOfBoundsException.class, () -> deque.get(-2));

            }

        }

        @Nested
        @DisplayName("and wants to know if the value given")
        class contains {
            @Test
            @DisplayName("is contained in the list")
            void testContainsValue() {
                assertThat(deque.contains(2), is(true));
            }

            @Test
            @DisplayName("is not contained in the list")
            void testContainsValueNotContained() {
                assertThat(deque.contains(4), is(false));
            }

        }

        @Nested
        @DisplayName("and wants to remove")
        class remove {

            @Test
            @DisplayName("the first element")
            void removeFirstElementR(){
                deque.remove(1);
                assertThat(deque.contains(1), is(false));

                int expectedValue = 2;
                int firstElement = deque.first();
                assertEquals(expectedValue, firstElement);
            }

            @Test
            @DisplayName("the last element")
            void removeLastElement(){
                deque.remove(3);
                assertThat(deque.contains(3), is(false));

                int expectedValue = 2;
                int lastElement = deque.get(deque.size()-1);
                assertEquals(expectedValue,lastElement);
            }

            @Test
            @DisplayName("the first occurrence of 1 so that first element is 2")
            void removeFirstOccurrenceOfAnElement(){
                deque.append(1);
                deque.remove(1);

                int expectedValue = 2;
                int firstElement = deque.first();
                assertEquals(expectedValue, firstElement);

                expectedValue = 1;
                int lastElement = deque.last();
                assertEquals(expectedValue, lastElement);
            }

            @Test
            @DisplayName("an element in the middle")
            void removeElementInTheMiddleOfADequeOfThree(){
                int expectedValue = 2;
                int actualValue = deque.get(deque.size()-2);
                assertEquals(expectedValue, actualValue);
            }
        }

        @Nested
        @DisplayName("and wants to sort")
        class sort {
            @Test
            @DisplayName("increasing")
            void sortListInNaturalOrder(){
                deque.prepend(5);
                deque.prepend(33);

                deque.sort(Comparator.naturalOrder());

                assertEquals(1, deque.get(0));
                assertEquals(2, deque.get(1));
                assertEquals(3, deque.get(2));
                assertEquals(5, deque.get(3));
                assertEquals(33, deque.get(4));
            }

            @Test
            @DisplayName("decreasing")
            void sortListInReverseOrder(){
                deque.append(5);
                deque.append(33);

                deque.sort(Comparator.reverseOrder());

                assertEquals(33, deque.get(0));
                assertEquals(5, deque.get(1));
                assertEquals(3, deque.get(2));
                assertEquals(2, deque.get(3));
                assertEquals(1, deque.get(4));
            }
            @Test
            @DisplayName("a list with one element")
            void sortListWithOneElement(){
                deque.remove(2);
                deque.remove(3);
                int expectedValue = 1;

                deque.sort(Comparator.naturalOrder());

                assertEquals(expectedValue, deque.first());
                assertEquals(expectedValue, deque.last());
            }
        }


    }
}