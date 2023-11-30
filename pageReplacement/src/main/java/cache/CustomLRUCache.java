package cache;

import static java.util.Objects.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class CustomLRUCache<K, V> {

    private static final int DEFAULT_SIZE = 5;
    private static final int INITIAL_SIZE = 0;


    private final Map<K, Node<K, V>> map = new HashMap<>();
    private final int maxSize;
    private int size = INITIAL_SIZE;
    private Node<K, V> head;
    private Node<K, V> tail;

    public CustomLRUCache(final int maxSize) {
        this.maxSize = maxSize;
    }

    public CustomLRUCache() {
        this(DEFAULT_SIZE);
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void save(final K key, final V value) {
        if (map.containsKey(key)) {
            overWrite(key, value);
            return;
        }

        if (size < maxSize) {
            saveNewNodeToEmptySpace(key, value);
            return;
        }

        if (size == maxSize) {
            saveWithReplacement(key, value);
        }

    }

    private void saveWithReplacement(final K key, final V value) {
        final Node<K, V> oldHead = this.head;
        final Node<K, V> newHead = oldHead.getNext();

        oldHead.setNext(null);
        final K headKey = oldHead.getKey();
        map.remove(headKey);

        newHead.setPrev(null);
        this.head = newHead;

        Node<K, V> newNode = new Node<>(key, value, tail, null);
        this.tail.setNext(newNode);
        this.tail = newNode;
        map.put(key, newNode);
    }

    private void saveNewNodeToEmptySpace(final K key, final V value) {
        Node<K, V> newNode = new Node<>(key, value, tail, null);
        final Node<K, V> tail = this.tail;

        if (size > 0) {
            tail.setNext(newNode);
        }

        if (size == 0) {
            this.head = newNode;
        }

        this.tail = newNode;
        map.put(key, newNode);
        size++;
    }

    private void overWrite(final K key, final V value) {
        final Node<K, V> originNode = getNode(key);
        originNode.setNext(null);
        originNode.setValue(value);

    }

    public V get(final K key) {
        return getNode(key).getValue();
    }

    private Node<K, V> getNode(final K key) {
        final Node<K, V> node = map.get(key);
        final Node<K, V> prev = node.getPrev();
        final Node<K, V> next = node.getNext();

        if(nonNull(prev)) {
            prev.setNext(next);
        }

        if(nonNull(next)) {
            next.setPrev(prev);
        }

        node.setPrev(tail);
        this.tail = node;
        return node;
    }

    public int getSize() {
        return size;
    }

    public Node<K, V> getHead() {
        return head;
    }

    public Node<K, V> getTail() {
        return tail;
    }
}
