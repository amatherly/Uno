import java.util.Iterator;

/**
 * A class representing a double linked list.
 * <p>
 * ASHLEIGH MATHERLY
 * <p>
 * PLEASE DO NOT COPY THIS FILE TO OTHER STUDENTS OR TO WEBSITES LIKE CHEGG,
 * GITHUB, ETC WHERE OTHERS MAY VIEW IT!!! IT IS YOUR WORK AND YOU SHOULD BE
 * PROUD OF WHAT YOU HAVE ACCOMPLISHED! IN ADDITION, THIS FILE CONTAINS THE
 * COPYRIGHTED WORK OF MARTIN HOCK AND IS ONLY LICENSED FOR USE BY INDIVIDUAL
 * STUDENTS FOR NONPROFIT EDUCATIONAL PURPOSES.
 */
public class PlayerList<T> implements Iterable<T> {

    public int size;  // To keep track of how many nodes are in the list


    public static class Node<T> {
        // before is reference to adjacent node closer to first (or null if this node is
        // the first)
        // after is reference to adjacent node closer to last (or null if this node is
        // the last)
        public Node<T> before, after;
        // data is the information stored in the node of type T (T could be String,
        // Integer, etc.)
        public T data;

        public Node(Node<T> before, T data, Node<T> after) {
            this.before = before;
            this.after = after;
            this.data = data;
        }
    }

    // first is beginning node (no before), last is end node (no after)
    // They can both reference the same node if the list is one element long
    // The can both reference null if the list is empty
    public Node <Player> first, last;

    /**
     * Forward iterator class (conductor).
     */
    private static class Conductor<Player> implements Iterator<Player> {
        public Node<Player> car; // Next node to visit

        public Conductor(PlayerList<Player> list) {
            car = (Node<Player>) list.first; // Begin at first
        }

        public boolean hasNext() {
            return car != null; // No more to visit
        }

        public Player next() {
            Player data = car.data; // Remember current
            car = car.after; // Advance to after car
            return data; // Return old car data
        }
    }

    private static class BackwardsConductor<T> implements Iterator<T> {
        public Node<T> car; // Next node to visit

        public BackwardsConductor(PlayerList<T> list) {
            car = (Node<T>) list.last; // Begin at last
        }

        public boolean hasNext() {
            return car != null; // No more to visit
        }

        public T next() {
            T data = car.data; // Remember current
            car = car.before; // Advance to before car
            return data; // Return old car data
        }
    }

    public PlayerList() {
        first = last = null; // Empty list
    }

    /**
     * Add data to the end (last) of the list.
     */
    public void add(T data) {

        if (last == null) {
            // Empty list: one node is first and last
            first = new Node<Player>(null, (Player) data, null); // creates a new node
            last = first;

        } else {
            // adds a new node to the end of the list
            last.after = new Node<Player>(last, (Player) data, null);
            last = last.after;
        }
        size++;
    }

    /**
     * Retrieve an element from middle of list.
     *
     * @param i Zero-based index of element
     * @return The element at that index
     * @throws IndexOutOfBoundsException if i is invalid
     */
    public Player get(int i) {

        // if the requested index is outside of the list, throw exception
        if (i < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node<Player> current = first;

        if (i <= size / 2) {

            for (int j = 0; j < i; j++) {
                // Count our way up to desired element
                current = current.after;
            }
        }
        if (i >= size / 2) {
            current = last;
            for (int j = 0; j < size - i - 1; j++) {
                current = current.before;
            }
        }

        if (current == null) {
            throw new IndexOutOfBoundsException();
        }
        return current.data;
    }

    /**
     * Get and remove element i from the list.
     *
     * @param i Zero-based index of element
     * @return The element at that index
     * @throws IndexOutOfBoundsException if i is invalid
     */
    public Player remove(int i) {

        if (i < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<Player> current = first;
        for (int j = 0; current != null && j < i; j++) {
            // Count our way up to desired element
            current = current.after;
        }
        if (current == null)
            throw new IndexOutOfBoundsException();
        if (current.before != null) {
            // Link before's after to new after
            // (The node after the node before the current node
            // becomes the node after the current node)
            current.before.after = current.after;
        } else {
            // current must be first, so first should refer to
            // the node after it to stop referencing current
            first = first.after;
        }
        if (current.after != null) {
            // Link after's before to new before
            // (The node before the node after the current node
            // becomes the node before the current node)
            current.after.before = current.before;
        } else {
            // current must be last, so last should refer to
            // the node before it to stop referencing current
            last = last.before;
        }
        size--;
        return (Player) current.data;
    }

    /**
     * Create a forward iterator for this list.
     *
     * @return iterator that walks from first to last
     */
    public Iterator<T> iterator() {
        // The Conductor object can walk this list
        // forward, front to back. Each time
        // .next() is called, the Conductor
        // produces one more piece of data,
        // starting with first and ending with last
        return new Conductor<T>(this);
    }

    /**
     * Create a reverse iterator for this list.
     *
     * @return iterator that walks from last to first
     */
    public Iterator<T> descendingIterator() {
        return new BackwardsConductor<T>(this);
    }

    /**
     * Retrieve the number of nodes of this list in O(1) time.
     *
     * @return number of nodes
     */
    public int size() {
        // returns the size of the doubly linked list aka how many nodes
        return size;
    }


    /**
     * Reverse the list, so that what was the last is now the first, and so forth. A
     * list going A <-> B <-> C <-> D would now go D <-> C <-> B <-> A.
     */
    public void reverse() {

        Node<T> temp;  // will be set to null
        Node<Player> current = first;  // start at the head of the list


        // reverses the entire list
        while (current != null) {

            temp = (Node<T>) current.before; // stores the current before into a temp node to use later
            current.before = current.after; // switches the references of the current node
            current.after = (Node<Player>) temp; // sets the after to the temp node from earlier
            current = current.before; // moves to the next node
        }

        // switches head and tail
        temp = (Node<T>) first;
        first = last;
        last = (Node<Player>) temp;
    }

    /**
     * Add data to a new node at index i, causing the nodes at that index and after
     * to be one place ahead of where they were in the list. (Do nothing if i was
     * not a valid index in the list.)
     *
     * @param i    existing index in the list
     * @param data information to add into a new node
     * @return false if i is not an index in the list, true otherwise
//     */
//    public boolean add(int i, T data) {
//
//        // if the index is bigger than the list or a negative number return false
//        if (i >= size || i < 0) {
//            return false;
//        }
//
//        // sets the current node to the head
//        Node<Player> current = first;
//
//        if (i > 0) {
//
//            // if closer to the beginning of the list
//            if (i <= size / 2) {
//                for (int j = 0; j < i; j++) {
//                    // Count our way up to desired element
//                    current = current.after;
//                }
//            }
//            // is index is closer to the end of the list, work backwards
//            if (i >= size / 2) {
//                current = last;  // start at the end
//                for (int j = 0; j < size - i - 1; j++) {
//                    current = current.before;
//                }
//            }
//        }
//        // If the index is 0, add to the beginning of the list and return, no further work needed
//        else {
//            first = new Node<T>(null, data, current);
//            current.before = first;
//            size++;
//            return true;
//        }

//        Node<T> newNode = new Node(current.before, data, current); // creates the new node
//        Node<T> temp = current.before; // saves the node for later use
//        current.before = newNode;
//        temp.after = newNode;
//        size++;
//        return true;
//    }
}
