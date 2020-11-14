package space.harbour.java.hm5;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
///////////
import java.util.Map;
import java.util.Set;


public class MyHashMap<K, V> implements Map<K, V> {

    public static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    private int bucketSize = 16;
    private LinkedList<Pair<K, V>>[] buckets = new LinkedList[bucketSize];

    public MyHashMap() {
        clear();
    }

    @Override
    public int size() {
        int result = 0;
        for (int i = 0; i < buckets.length; i++) {
            result += buckets[i].size();
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private int keyToBucketIndex(Object key) {
        return Math.abs(key.hashCode() % bucketSize);
    }

    @Override
    public boolean containsKey(Object key) {
        int i = keyToBucketIndex(key);
        for (Pair<K, V> pair : buckets[i]) {
            if (pair.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < buckets.length; i++) {
            for (Pair<K, V> pair : buckets[i]) {
                if (pair.getValue().equals(value)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public V get(Object key) {
        int i = keyToBucketIndex(key);
        if (containsKey(key)) {
            for (Pair<K, V> pair : buckets[i]) {
                if (pair.getKey().equals(key)) {
                    return pair.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        Pair<K, V> newPair = new Pair<>(key, value);
        int i = keyToBucketIndex(key);
        if (containsKey(key)) {
            for (Pair<K, V> pair : buckets[i]) {
                if (pair.getKey().equals(key)) {
                    pair.value = newPair.getValue();
                }
            }
        } else {
            buckets[i].add(newPair);
        }
        return null;
    }

    @Override
    public V remove(Object key) {
        int i = keyToBucketIndex(key);

        if (containsKey(key)) {
            for (Pair<K, V> pair : buckets[i]) {
                if (pair.key.equals(key)) {
                    buckets[i].remove(pair);
                    return pair.getValue();
                }
            }
        }
        return null;
    }


    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> pair : m.entrySet()) {
            put(pair.getKey(), pair.getValue());
        }
    }


    @Override
    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    @Override
    public Set<K> keySet() {
        return null;
    }


    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entrySet = new HashSet<>();
        for (LinkedList<MyHashMap.Pair<K, V>> bucket : buckets) {
            for (MyHashMap.Pair<K, V> pair : bucket) {
                entrySet.add(new AbstractMap.SimpleEntry<K, V>(pair.getKey(), pair.getValue()));
            }
        }
        return entrySet;
    }
}
