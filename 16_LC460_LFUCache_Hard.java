import java.util.HashMap;
import java.util.Map;

class LFUCache {

    private static class Node {
        int key;
        int value;
        int frequency = 1;

        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private static class DoublyLinkedList {
        private final Node head;
        private final Node tail;
        private int size;

        DoublyLinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);

            head.next = tail;
            tail.prev = head;
        }

        void addFirst(Node node) {
            node.prev = head;
            node.next = head.next;

            head.next.prev = node;
            head.next = node;

            size++;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;

            size--;
        }

        Node removeLast() {
            Node oldest = tail.prev;
            remove(oldest);
            return oldest;
        }

        boolean isEmpty() {
            return size == 0;
        }
    }

    private final int capacity;
    private int minFrequency;

    private final Map<Integer, Node> cache;
    private final Map<Integer, DoublyLinkedList> frequencyLists;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.frequencyLists = new HashMap<>();
    }

    public int get(int key) {
        Node node = cache.get(key);

        if (node == null) {
            return -1;
        }

        increaseFrequency(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }

        Node existing = cache.get(key);

        if (existing != null) {
            existing.value = value;
            increaseFrequency(existing);
            return;
        }

        if (cache.size() == capacity) {
            DoublyLinkedList minList =
                    frequencyLists.get(minFrequency);

            Node evicted = minList.removeLast();
            cache.remove(evicted.key);

            if (minList.isEmpty()) {
                frequencyLists.remove(minFrequency);
            }
        }

        Node newNode = new Node(key, value);
        cache.put(key, newNode);

        frequencyLists
                .computeIfAbsent(1, ignored -> new DoublyLinkedList())
                .addFirst(newNode);

        minFrequency = 1;
    }

    private void increaseFrequency(Node node) {
        int oldFrequency = node.frequency;
        DoublyLinkedList oldList =
                frequencyLists.get(oldFrequency);

        oldList.remove(node);

        if (oldList.isEmpty()) {
            frequencyLists.remove(oldFrequency);

            if (minFrequency == oldFrequency) {
                minFrequency++;
            }
        }

        node.frequency++;

        frequencyLists
                .computeIfAbsent(
                        node.frequency,
                        ignored -> new DoublyLinkedList()
                )
                .addFirst(node);
    }
}
