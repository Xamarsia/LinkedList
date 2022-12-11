import java.util.Iterator;

public class LinkedList<Type> implements LinkedListInterface<Type> {
    private Node<Type> firstNode = null;
    private Node<Type> lastNode = null;
    private int size = 0;

    static class Node<Type> {
        private Node<Type> previousNode;
        private Type value;
        private Node<Type> nextNode;

        public Node(Type value) {
            this.previousNode = null;
            this.value = value;
            this.nextNode = null;
        }

        public Node(Node<Type> previousNode, Type value, Node<Type> nextNode) {
            this.previousNode = previousNode;
            this.value = value;
            this.nextNode = nextNode;
        }

        public Node<Type> getPrevious() {
            return previousNode;
        }

        public Type getValue() {
            return value;
        }

        public Node<Type> getNext() {
            return nextNode;
        }

        public void setPrevious(Node<Type> previous) {
            previousNode = previous;
        }

        public void setValue(Type value) {
            this.value = value;
        }

        public void setNext(Node<Type> next) {
            nextNode = next;
        }
    }

    public static class ListIterator<Type> implements Iterator<Type> {
        Node<Type> currentNode;

        public ListIterator(LinkedList<Type> list) {
            currentNode = list.firstNode;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public Type next() {
            Type value = currentNode.getValue();
            currentNode = currentNode.getNext();
            return value;
        }
    }

    @Override
    public boolean contains(Type value) {
        for (Type currentValue : this) {
            if (currentValue == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Type remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        } else if (index == 0) {
            removeFirst();
        } else if (index == size - 1) {
            removeLast();
        } else {
            Node<Type> currentNode = getNodeByIndex(index);
            if (currentNode != null) {
                currentNode.getPrevious().setNext(currentNode.getNext());
                currentNode.getNext().setPrevious(currentNode.getPrevious());
                --size;
                return currentNode.getValue();
            }
        }
        return null;
    }

    @Override
    public void add(int index, Type value) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        } else if (index == 0) {
            addFirst(value);
        } else if (index == size) {
            addLast(value);
        } else {
            Node<Type> currentNode = getNodeByIndex(index);
            if (currentNode != null) {
                Node<Type> newNode = new Node<>(currentNode.getPrevious(), value, currentNode);

                currentNode.getPrevious().setNext(newNode);
                currentNode.setPrevious(newNode);
                ++size;
            }
        }
    }

    @Override
    public Boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    public Type getFirst() {
        return firstNode.getValue();
    }

    public Type getLast() {
        return lastNode.getValue();
    }

    @Override
    public void addFirst(Type type) {
        if (isEmpty()) {
            Node<Type> node = new Node<>(type);
            firstNode = node;
            lastNode = node;
        } else {
            Node<Type> node = new Node<>(null, type, firstNode);
            firstNode.setPrevious(node);
            firstNode = node;
        }
        ++size;
    }

    @Override
    public void addLast(Type type) {
        if (isEmpty()) {
            Node<Type> node = new Node<>(type);
            firstNode = node;
            lastNode = node;
        } else {
            Node<Type> node = new Node<>(lastNode, type, null);
            lastNode.setNext(node);
            lastNode = node;
        }
        ++size;
    }

    @Override
    public void removeFirst() {
        if (isEmpty()) {
            return;
        } else if (size == 1) {
            firstNode = null;
            lastNode = null;
        } else {
            firstNode = firstNode.getNext();
            firstNode.setPrevious(null);
        }
        --size;
    }

    @Override
    public void removeLast() {
        if (isEmpty()) {
            return;
        } else if (size == 1) {
            firstNode = null;
            lastNode = null;
        } else {
            lastNode = lastNode.getPrevious();
            lastNode.setNext(null);
        }
        --size;
    }

    @Override
    public Iterator<Type> iterator() {
        return new ListIterator<>(this);
    }

    private Node<Type> getNodeByIndex(int index) throws IndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        } else if (index == 0) {
            return firstNode;
        } else if (index == size - 1) {
            return lastNode;
        } else {
            Node<Type> currentNode;
            // if index in first part
            if (index <= size / 2) {
                currentNode = firstNode;
                for (int i = 0; i < index; i++) {
                    currentNode = currentNode.getNext();
                }
            } else {
                currentNode = lastNode;
                for (int i = size - 1; i > index; --i) {
                    currentNode = currentNode.getPrevious();
                }
            }
            return currentNode;
        }
    }
}
