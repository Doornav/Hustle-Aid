package com.example.hustleaid;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Maps<K, V> {

    private static final int NUM_BUCKETS = 16;
    private MapEntry<K, V>[] buckets;
    private int size;

    public Maps() {
        this.initialize();
    }

    private void initialize() {
        this.buckets = new MapEntry[NUM_BUCKETS];
        this.size = 0;
    }

    public void setContents(List<MapEntryContent<K,V>> contents) {
        this.initialize();

        for (MapEntryContent<K,V> content: contents) {
            this.put(content.getKey(), content.getValue());
        }
    }

    public List<MapEntryContent<K,V>> getContents() {
        List<MapEntryContent<K,V>> contents = new ArrayList<>();

        for (int i = 0; i < NUM_BUCKETS; i++) {
            MapEntry<K,V> entry = this.buckets[i];
            while (entry != null) {
                contents.add(entry.content);
                entry = entry.next;
            }
        }

        return contents;
    }

    public void put(K key, V value) {

        MapEntry<K,V> newEntry = new MapEntry<>(key, value, null, null);

        int bucketIndex = getBucketIndex(key);
        MapEntry <K,V> existing = buckets[bucketIndex];
        if (existing == null) {
            buckets[bucketIndex] = newEntry;
        } else {
            //must check to see if the key already exists
            while (existing.next != null) {
                if (existing.getKey().equals(key)) {
                    existing.setValue(value);
                    return;
                }
                existing = existing.next;
            }

            if (existing.getKey().equals(key)) {
                existing.setValue(value);
            } else {
                newEntry.prev = existing;
                existing.next = newEntry;
                this.size++;
            }
        }
    }

    public V get(K key) {
        MapEntry<K, V> bucket = buckets[getBucketIndex(key)];
        while (bucket != null) {
            if (bucket.getKey().equals(key)) {
                return bucket.getValue();
            }
            bucket = bucket.next;
        }
        throw new NoSuchElementException("No such key found.");
    }

    private int getBucketIndex(K key) {
        return (getHash(key) % NUM_BUCKETS);
    }

    public boolean containsKey(K key) {
        MapEntry<K, V> bucket = buckets[getHash(key) % NUM_BUCKETS];
        while (bucket != null) {
            if (bucket.getKey().equals(key)) {
                return true;
            }
            bucket = bucket.next;
        }
        return false;
    }

    public V remove(K key) {
        MapEntry<K, V> bucket = buckets[getBucketIndex(key)];
        while (bucket != null) {
            if (bucket.getKey().equals(key)) {
                V temp = bucket.getValue();
                if (bucket.prev == null) {
                    bucket = bucket.next;
                    bucket.prev = null;
                } else {
                    bucket.prev.next = bucket.next;
                }
                this.size--;
                return temp; //returning value at the removed key
            }
            bucket = bucket.next;
        }
        throw new NoSuchElementException("No such key found"); //if the key is not found
    }

    private int getHash(K key) {
        if (key == null) {
            return 0;
        } else {
            return Math.abs(key.hashCode());
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (MapEntry e: buckets) {
            sb.append("[");
            while (e != null) {
                sb.append(e);
                if (e.next != null) {
                    sb.append(",");
                }
                e = e.next;
            }
            sb.append("]");
        }
        return sb.toString();
    }

    public K[] keySet() {
        K[] keys = (K[]) new Object[this.size];
        int keyIndex = 0;
        for (int i = 0; i < NUM_BUCKETS; i++) {
            MapEntry<K,V> entry = this.buckets[i];
            while (entry != null) {
                keys[keyIndex++] = entry.getKey();
                entry = entry.next;
            }
        }

        return keys;
    }
}