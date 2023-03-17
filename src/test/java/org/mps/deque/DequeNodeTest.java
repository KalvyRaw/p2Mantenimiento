package org.mps.deque;

import org.junit.jupiter.api.*;
import org.mps.deque.DequeNode;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author brisanx (Alba Sanchez Ibanez) Fernando Calvo Diaz
 *
 */
@DisplayName("Tests results for the DequeNode")
class DequeNodeTest {
    DequeNode<Integer> previous;
    DequeNode<Integer> node;
    DequeNode<Integer> next;
    DequeNode<Integer> extra;
    @BeforeEach
    void setup(){
        previous = new DequeNode<>(1,null, null);
        node = new DequeNode<>(2, null, null);
        next = new DequeNode<>(3, null,null);
        extra = new DequeNode<>(4, null,null);
    }
    @AfterEach
    void shutdown(){
        previous = null;
        node = null;
        next = null;
        extra = null;
    }
    @Nested
    @DisplayName("get method")
    class getters {
        @Nested
        @DisplayName("of previous node")
        class previousNode {
            @DisplayName("of first node returns null")
            @Test
            void previousOfFirstNodeShouldReturnNull(){
                previous.setNext(node);
                DequeNode<Integer> actualValue = previous.getPrevious();
                assertNull(actualValue);
            }

            @DisplayName("of middle node")
            @Test
            void previousOfMiddleNodeShouldReturnCorrectly(){
                previous.setNext(node);
                node.setPrevious(previous);
                node.setNext(next);
                next.setPrevious(node);
                DequeNode<Integer> expectedValue = previous;
                DequeNode<Integer> actualValue = node.getPrevious();
                assertEquals(expectedValue,actualValue);
            }

            @DisplayName("of last node")
            @Test
            void previousOfLastNodeShouldReturnCorrectly(){
                previous.setNext(node);
                node.setPrevious(previous);
                node.setNext(next);
                next.setPrevious(node);
                DequeNode<Integer> expectedValue = node;
                DequeNode<Integer> actualValue = next.getPrevious();
                assertEquals(expectedValue,actualValue);
            }
        }
        @Nested
        @DisplayName("next node")
        class nextNode {
            @Test
            @DisplayName("of last node is null")
            void nextNodeOfLastNodeShouldReturnNull(){
                next.setNext(null);
                DequeNode<Integer> actualValue = next.getNext();
                assertNull(actualValue);
            }

            @Test
            @DisplayName("of middle node")
            void nextNodeOfMiddleNodeShouldReturnCorrectly(){
                previous.setNext(node);
                node.setPrevious(previous);
                node.setNext(next);
                next.setPrevious(node);
                DequeNode<Integer> expectedValue = next;
                DequeNode<Integer> actualValue = node.getNext();
                assertEquals(expectedValue,actualValue);
            }

            @Test
            @DisplayName("of first node")
            void nextNodeOfFirstNode(){
                previous.setNext(node);
                DequeNode<Integer> expectedValue = node;
                DequeNode<Integer> actualValue = previous.getNext();
                assertEquals(expectedValue,actualValue);
            }
        }
        @Test
        @DisplayName("item of a node returns its item")
        void getItemReturnsItemCorrectly(){
            int expectedValue = 1;
            int actualValue = previous.getItem();
            assertEquals(expectedValue, actualValue);
        }

    }
    @Nested
    @DisplayName("set method")
    class setters {
        @Test
        @DisplayName("sets item of node with a new value")
        void setItemSetsTheItemCorrectly(){
            int expectedValue = 33;
            previous.setItem(33);
            int actualValue = previous.getItem();
            assertEquals(expectedValue, actualValue);
        }
        @Test
        @DisplayName("previous node to a node")
        void setPreviousShouldWorkProperly(){
            extra.setPrevious(next);
            next.setNext(extra);
            DequeNode<Integer> expectedValue = next;
            DequeNode<Integer> actualValue = extra.getPrevious();
            assertEquals(expectedValue,actualValue);
        }
        @Test
        @DisplayName("next node to a node")
        void setNextShouldWorkProperly(){
            extra.setNext(next);
            next.setPrevious(extra);
            DequeNode<Integer> expectedValue = next;
            DequeNode<Integer> actualValue = extra.getNext();
            assertEquals(expectedValue,actualValue);
        }
    }
    @Nested
    @DisplayName("check if node")
    class checkNodes {
        @Nested
        class isFirstNode {
            @Test
            @DisplayName("when you are first node returns true")
            void isFirstNodeShouldReturnTrueIfItsFirst(){
                assertTrue(previous.isFirstNode());
            }
            @Test
            @DisplayName("when you are not first node returns false")
            void isFirstNodeIfYouArentFirstNodeShouldReturnFalse(){
                previous.setNext(node);
                node.setPrevious(previous);
                node.setNext(next);
                next.setPrevious(node);
                assertFalse(node.isFirstNode());
                assertFalse(next.isFirstNode());
            }
        }

        @Nested
        class isLastNode {
            @Test
            @DisplayName("when you are last node returns true")
            void isLastNodeShouldReturnTrueIfItsLast(){
                assertTrue(next.isLastNode());
            }
            @Test
            @DisplayName("when you are not last node returns false")
            void isLastNodeIfYouArentLastNodeShouldReturnFalse() {
                assertTrue(node.isLastNode());
                previous.setNext(node);
                node.setNext(next);
                assertFalse(node.isLastNode());
                assertFalse(previous.isLastNode());
            }
        }
        @Nested
        class isNotTerminalNode {
            @Test
            @DisplayName("when you are not terminal node returns true")
            void nodeThatIsNotFirstOrFinalWillReturnTrue(){
                assertFalse(node.isNotATerminalNode());
                node.setPrevious(previous);
                node.setNext(next);
                assertTrue(node.isNotATerminalNode());
            }
            @Test
            @DisplayName("when you are terminal node returns false")
            void nodeThatIsFirstOrLastWillBeTerminalNode(){
                previous.setNext(node);
                node.setPrevious(previous);
                node.setNext(next);
                next.setPrevious(node);
                assertFalse(previous.isNotATerminalNode());
                assertFalse(next.isNotATerminalNode());
            }
        }

    }
}