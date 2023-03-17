package com.example.hustleaid;


public class MapEntry<K, V> {

    MapEntryContent<K,V> content;
    MapEntry<K,V> next;
    MapEntry<K,V> prev;

    public MapEntry() {
    }

    public MapEntry(
            K key,
            V value,
            MapEntry<K, V> next,
            MapEntry<K, V> prev)
    {
        this.content = new MapEntryContent(key, value);
        this.next = next;
        this.prev = prev;
    }

    public void setKey(K key) {
        this.content.setKey(key);
    }

    public void setValue(V value) {
        this.content.setValue(value);
    }

    public void setNext(MapEntry<K, V> next) {
        this.next = next;
    }

    public void setPrev(MapEntry<K, V> prev) {
        this.prev = prev;
    }

    public K getKey() {
        return content.getKey();
    }

    public V getValue() {
        return content.getValue();
    }

    public MapEntry<K, V> getNext() {
        return next;
    }

    public MapEntry<K, V> getPrev() {
        return next;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getKey());
        sb.append("=");
        sb.append(getValue());
        return sb.toString();
    }
}