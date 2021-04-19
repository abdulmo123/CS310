package edu.sdsu.cs.datastructures;

public class SinglyLinkedList<E> implements List<E> {
    private Node head;
    private Node tail;
    private int size;

    class Node {
        Node next;
        E datum;

        public Node(E datum) {
            this.datum = datum;
            next = null;
        }
    }

    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public SinglyLinkedList(List<E> other) {
        this.head = null;
        this.tail = null;
        this.size = 0;

        for (int i = 0; i < other.size(); i++) {
            this.add(other.get(i));
        }
    }

    @Override
    public boolean add(E datum) {
        Node node = new Node(datum);

        if (size == 0) {
            head = node; // if list is empty, head gets node
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = node; // appends added nodes to end
        }

        size++;
        return true;
    }

    @Override
    public boolean add(List<E> other) {
        // loops through 'other' and gets items and adds them to list
        for (int i = 0; i < other.size(); i++) {
            this.add(other.get(i));
        }
        return true;
    }

    @Override
    public boolean addFirst(E datum) {
        Node newHead = new Node(datum);
        if (head == null) {
            head = tail = newHead;
            // if list is empty
            // add node at front
            // make head and tail point to new node

        } else {
            newHead.next = head; // new node's next node points to head
            head = newHead; // head points to new node
        }

        ++size;
        return true;
    }

    @Override
    public boolean addLast(E datum) {
        Node newTail = new Node(datum);
        Node current = head;

        if (head == null) {
            head = newTail; // if list is empty, head and tail point to newTail
        } else {
            while (current.next != null) {
                current = current.next; // loops to end
            }
            current.next = newTail;
            // finds end of list and adds to end and makes it the new tail
        }

        ++size;
        return true;
    }

    @Override
    public void removeFirst() {
        if (head == null) {
            return; // nothing to remove
        }

        Node node = head; // node gets the head
        head = head.next; // head pointer moves over to head's next
        size--;

    }

    @Override
    public void removeLast() {
        // iterates till the end and removes the last element in the list
        // (second to last element becomes the new tail)
        Node current = head;

        if (head == null || head.next == null) {
            return;
        }

        while (current.next.next != null) {
            current = current.next;
        }

        current.next = tail = null;
        size--;

    }

    @Override
    public void clear() {
        // clears the list and makes head and tail null
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int count(E target) {
        // iterate through the list and count how many times
        // the target value was found in the list

        int counter = 0;
        Node current = head;

        while (current != tail) {
            if (((Comparable<E>) target).compareTo(current.datum) == 0) {
                counter++;
            }

            current = current.next;
        }
        return counter;
    }

    @Override
    public E get(int index) {
        if (index < 0) {
            index = (size + index) % size; // accounts for negative indices
        }

        Node current = head;
        int target = 0;

        while (current != tail) {

            if (target == index) {
                return current.datum; // if target is index, return its datum
            } else {
                target++; // increment counter
                current = current.next; // continue traversing through list
            }
        }

        return current.datum;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public E remove(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        if (index < 0) {
            index = (size + index) % size; // accounts for negative indices
        }

        Node current = head;
        Node temp = head;

        if (head == null) {
            return null;

        } else if (index == 0) {
            removeFirst(); // if index is 0 (remove first item in list -- head)

        } else if (index == size() - 1) {
            removeLast(); // removes if index is last item in list (tail)

        } else {
            while (index > 0 && index <= size) {

                current = temp; // current gets temp
                temp = temp.next; // temp gets its next node
                index--; // decrement index by 1
            }

            current.next = temp.next;
            temp = null; // delete temp node (removes item)
            size--; // decrement size by 1

        }

        return current.datum;
    }

    @Override
    public void reverse() {
        if (head == null || head.next == null) {
            return;
        }

        Node current = head;
        Node reversed = null;

        while (current != tail) {
            // in each iteration, the pointer's direction is 'flipped'
            // so that it points to its previous element instead of the next element

            Node reversible = current.next;
            current.next = reversed;
            reversed = current;
            current = reversible;
        }

        head = reversed; // last element becomes the new head
    }

    @Override
    public E set(int index, E value) {
        if (index < 0) {
            index = (size + index) % size; // accounts for negative indices
        }

        Node current = head;
        int target = 0;

        E prevValue = null; // variable that stores the item that will be changed

        while (current != null) {

            if (target == index) {
                prevValue = current.datum; // prevValue gets datum at current index
                current.datum = value; // current index gets new value
            }

            current = current.next;
            target++;
        }

        return prevValue; // returns value previously stored at index
    }

    @Override
    public int size() {
        return size; // returns current size of list
    }

    @Override
    public void sort() {
        if (head == null || head.next == null) {
            return;
            // if head is null OR head.next is null
            // (meaning only 1 element in list)
            // there's nothing to sort
        }

        Node current = head;
        Node minimum;
        Node temp;

        while (current != null) {

            minimum = current; // min starts with current
            temp = current.next; // temp starts at current's next node

            while (temp != null) {

                if (((Comparable<E>) temp.datum).compareTo(minimum.datum) <= 0) {
                    minimum = temp;
                    // if temp's datum is less than (or equal to) min's datum
                    // min gets temp
                }

                temp = temp.next; // continue iteration
            }

            E sorted = current.datum;
            current.datum = minimum.datum; // swapping datum's of elements in list
            minimum.datum = sorted;

            current = current.next;
        }
    }

    @Override
    public String toString() {
        // created a string builder to visualize list
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Node list = head;

        while (list != null && list.next != null) {
            sb.append(list.datum).append(" -> ");
            list = list.next;
        }
        if (list != null) {
            sb.append(list.datum);
        }

        sb.append("]");

        return sb.toString();
    }
}