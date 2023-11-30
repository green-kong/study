package cache;

class Node<K, V> {

    private K key;
    private V value;
    private Node<K, V> prev;
    private Node<K, V> next;

    public Node(final K key, final V value, final Node<K, V> prev, final Node<K, V> next) {
        this.key = key;
        this.value = value;
        this.prev = prev;
        this.next = next;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public Node<K, V> getPrev() {
        return prev;
    }

    public Node<K, V> getNext() {
        return next;
    }

    public void setKey(final K key) {
        this.key = key;
    }

    public void setValue(final V value) {
        this.value = value;
    }

    public void setPrev(final Node<K, V> prev) {
        this.prev = prev;
    }

    public void setNext(final Node<K, V> next) {
        this.next = next;
    }
}
