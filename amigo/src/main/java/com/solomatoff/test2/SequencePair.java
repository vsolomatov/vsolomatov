package com.solomatoff.test2;

import java.util.Map;
import java.util.TreeMap;

public class SequencePair<K extends Comparable, V> {
    private TreeMap<K, V> mapSequencePair = new TreeMap<>();

    public void addPairToSequence(K key, V value) {
        mapSequencePair.put(key, value);
    }

    public void removePairFromSequence(K key) {
        mapSequencePair.remove(key);
    }

    public TreeMap<K, V> getMapSequencePair() {
        return mapSequencePair;
    }

    public void setMapSequencePair(TreeMap<K, V> mapSequencePair) {
        this.mapSequencePair = mapSequencePair;
    }
}
