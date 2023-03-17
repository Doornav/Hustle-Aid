package com.example.hustleaid;


public class MapEntryContent<K,V> {
    K key;
    V value;

    public MapEntryContent() {
    }

    public MapEntryContent(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}