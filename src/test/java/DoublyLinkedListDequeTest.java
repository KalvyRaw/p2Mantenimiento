import org.junit.jupiter.api.*;
import org.mps.deque.DoubleEndedQueueException;
import org.mps.deque.DoublyLinkedListDeque;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;

@DisplayName("DoublyLinkedListDeque tests")
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
            assertEquals(0, deque.size());
        }

        @Test
        @DisplayName("and delete firt element should throw DoubleEndedQueueException")
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
            assertEquals(2, deque.size());
            assertEquals(2, deque.first());
        }

        @Test
        @DisplayName("delete last element should remove the last element")
        void deleteLastShouldRemoveLastElement() {
            deque.deleteLast();
            assertEquals(2, deque.size());
            assertEquals(2, deque.last());
        }

        @Test
        @DisplayName("prepend should add an element at the beginning")
        void prependShouldAddElementAtBeginning() {
            deque.prepend(0);
            assertEquals(4, deque.size());
            assertEquals(0, deque.first());
        }

        @Test
        @DisplayName("append should add an element at the end")
        void appendShouldAddElementAtEnd() {
            deque.append(4);
            assertEquals(4, deque.size());
            assertEquals(4, deque.last());
        }
    }
}
