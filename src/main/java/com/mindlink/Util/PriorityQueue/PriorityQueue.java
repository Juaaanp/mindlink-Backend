package com.mindlink.Util.PriorityQueue;

import lombok.Data;
@Data
public class PriorityQueue<T extends Comparable<T>> {
    protected Node<T> nodeBeggining;
    protected Node<T> nodeEnding;
    protected int size;

    public PriorityQueue() {
        nodeBeggining = nodeEnding = null;
        size = 0;
    }

    // Insert with priority
    public void insert(T value) {
        Node<T> newNode = new Node<>(value);

        if (isEmpty()) {
            // if is empty, the new node is the beggining as well as ending.
            nodeBeggining = nodeEnding = newNode;
        } else if (value.compareTo(nodeBeggining.getValue()) > 0) {
            // if the new node has more priority than the first one
            newNode.setNextNode(nodeBeggining);
            nodeBeggining = newNode;
        } else {
            // search the right position in the list.
            Node<T> current = nodeBeggining;
            while (current.getNextNode() != null &&
                    value.compareTo(current.getNextNode().getValue()) <= 0) {
                current = current.getNextNode();
            }
            newNode.setNextNode(current.getNextNode());
            current.setNextNode(newNode);

            // update the ending node if is necessary
            if (newNode.getNextNode() == null) {
                nodeEnding = newNode;
            }
        }
        size++;
    }

    // Eliminate the node with most priority (Queue's head)
    public T eliminate() {
        if (isEmpty()) {
            return null; // Empty Queue
        }
        T highestPriorityValue = nodeBeggining.getValue();
        nodeBeggining = nodeBeggining.getNextNode();
        size--;

        if (isEmpty()) {
            nodeEnding = null;
        }
        return highestPriorityValue;
    }

    // Method poll: Removes and returns the element with the highest priority (head of the queue)
    public T poll() {
        if (isEmpty()) {
            return null; // If the queue is empty, return null
        }
        T highestPriorityValue = nodeBeggining.getValue(); // Get the value with the highest priority
        nodeBeggining = nodeBeggining.getNextNode(); // Move the head to the next node
        size--; // Decrease the size of the queue

        if (isEmpty()) {
            nodeEnding = null; // If the queue is empty after removal, set the ending node to null
        }
        return highestPriorityValue;
    }

    // Method peek: Returns the element with the highest priority without removing it
    public T peek() {
        if (isEmpty()) {
            return null; // If the queue is empty, return null
        }
        return nodeBeggining.getValue(); // Return the value at the head of the queue
    }

    // IsEmpty
    public boolean isEmpty() {
        return nodeBeggining == null;
    }

}
