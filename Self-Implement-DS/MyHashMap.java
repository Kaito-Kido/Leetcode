import java.util.LinkedList;

class MyHashMap {
    private static final int SIZE = 769; // A prime number for reducing collisions
    private LinkedList<Entry>[] buckets;

    // Key-Value pair
    private static class Entry {
        int key, value;

        Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public MyHashMap() {
        buckets = new LinkedList[SIZE];
        for (int i = 0; i < SIZE; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    private int hash(int key) {
        return key % SIZE; // Hash function
    }

    public void put(int key, int value) {
        int index = hash(key);
        LinkedList<Entry> bucket = buckets[index];

        // Check if key already exists, then update value
        for (Entry entry : bucket) {
            if (entry.key == key) {
                entry.value = value;
                return;
            }
        }

        // Add a new key-value pair
        bucket.add(new Entry(key, value));
    }

    public int get(int key) {
        int index = hash(key);
        LinkedList<Entry> bucket = buckets[index];

        for (Entry entry : bucket) {
            if (entry.key == key) {
                return entry.value;
            }
        }
        return -1; // Key not found
    }

    public void remove(int key) {
        int index = hash(key);
        LinkedList<Entry> bucket = buckets[index];

        bucket.removeIf(entry -> entry.key == key);
    }
}
