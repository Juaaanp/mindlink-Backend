package com.mindlink.Util.DoublyLinkedList;

//This class is now deprecated

public class DoublyLinkedList<E> {
    private Node<E> nodeBeginning, nodeEnding;
    private int size;

    public DoublyLinkedList() {
        this.nodeBeginning = null;
        this.nodeEnding = null;
        this.size = 0;
    }

    public void addBeginning(E value) {
        Node<E> node = new Node<>(value);
        if (nodeBeginning == null) { //if the list is empty.
            nodeBeginning = nodeEnding = node;
        } else {
            node.setNextNode(nodeBeginning);
            node.setPreviousNode(null);
            nodeBeginning = node;
        }
        size++;
    }

    public void addEnding(E value) {
        Node<E> node = new Node<>(value);
        if (nodeEnding == null) {
            nodeEnding = nodeBeginning = node;
        } else {
            node.setPreviousNode(nodeEnding);
            nodeEnding.setNextNode(node);
            nodeEnding = node;
        }
        size++;
    }

    //This method returns the element's position if it's present in the list, else it returns -1.
    public int search(E value) {
        Node<E> current = nodeBeginning;
        int index = 0;
        while (current != null) {
            if (current.getValue().equals(value)) {
                return index;
            }
            current = current.getNextNode();
            index++;
        }
        return -1;
    }

    public void show() {
        if (nodeBeginning == null) {
            System.err.println("The list is empty.");
            return;
        }
        Node<E> current = nodeBeginning;
        while (current != null) {
            System.out.println(current.getValue() + "->");
            current = current.getNextNode();
        }
    }

    public void eliminate(E value) {
        if (nodeBeginning == null) {
            System.out.println("The list is empty");
            return;
        }
        //Eliminate the first node case
        if (nodeBeginning.getValue().equals(value)) {
            if (nodeBeginning.getNextNode() != null) {
                nodeBeginning = nodeBeginning.getNextNode();
                nodeBeginning.setPreviousNode(null);
            } else {
                nodeEnding = null;
            }
            size--;
            return;
        }
        Node<E> current = nodeBeginning;
        while (current != null && !current.getValue().equals(value)) {
            current = current.getNextNode();
        }

        if (current == null) {
            System.err.println("The value is not found in the list.");
            return;
        }

        // Reconnect the nodes for eliminate the current node.
        Node<E> prev = current.getPreviousNode();
        Node<E> next = current.getNextNode();
        if (prev != null) {
            prev.setNextNode(next);
        }
        if (next != null) {
            next.setPreviousNode(prev);
        }

        // If the final node is eliminated.
        if (current == nodeEnding) {
            nodeEnding = prev;
        }

        size--;
    }

    public Node<E> getNodeBeginning() {
        return this.nodeBeginning;
    }

    public Node<E> getNodeEnding() {
        return this.nodeEnding;
    }

    public int getSize() {
        return this.size;
    }

    public void setNodeBeginning(Node<E> nodeBeginning) {
        this.nodeBeginning = nodeBeginning;
    }

    public void setNodeEnding(Node<E> nodeEnding) {
        this.nodeEnding = nodeEnding;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof DoublyLinkedList)) return false;
        final DoublyLinkedList<?> other = (DoublyLinkedList<?>) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$nodeBeginning = this.getNodeBeginning();
        final Object other$nodeBeginning = other.getNodeBeginning();
        if (this$nodeBeginning == null ? other$nodeBeginning != null : !this$nodeBeginning.equals(other$nodeBeginning))
            return false;
        final Object this$nodeEnding = this.getNodeEnding();
        final Object other$nodeEnding = other.getNodeEnding();
        if (this$nodeEnding == null ? other$nodeEnding != null : !this$nodeEnding.equals(other$nodeEnding))
            return false;
        if (this.getSize() != other.getSize()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DoublyLinkedList;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $nodeBeginning = this.getNodeBeginning();
        result = result * PRIME + ($nodeBeginning == null ? 43 : $nodeBeginning.hashCode());
        final Object $nodeEnding = this.getNodeEnding();
        result = result * PRIME + ($nodeEnding == null ? 43 : $nodeEnding.hashCode());
        result = result * PRIME + this.getSize();
        return result;
    }

    public String toString() {
        return "DoublyLinkedList(nodeBeginning=" + this.getNodeBeginning() + ", nodeEnding=" + this.getNodeEnding() + ", size=" + this.getSize() + ")";
    }
}
