package com.solomatoff.test2;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TreeMap;

/**
 * Класс для объединения двух последовательностей в новую последовательность,
 * которая будет соответствовать интервалу [startdt_2, enddt_2] и содержать значения из обеих последовательностей.
 * Здесь startdt2 - это минимальное значение ключа второй последовательности,
 *       enddt_2 - это максимальное значение ключа второй последовательности.
 * @param <K> тип ключа последовательности
 * @param <V> тип значения последовательности
 */
public class MainSequence<K extends Comparable, V> {
    /**
     * Метод для объединения двух последовательностей в новую последовательность,
     * которая будет соответствовать интервалу [startdt_2, enddt_2] и содержать значения из обеих последовательностей.
     * Здесь startdt2 - это минимальное значение ключа второй последовательности,
     *       enddt_2 - это максимальное значение ключа второй последовательности.
     * Если какое-то значение ключа присутствует в обеих последовательностях, то сохраняется значение из второй последовательности.
     * @param sequencePair1 первая последовательность
     * @param sequencePair2 вторая последовательность
     * @return объединенную последовательность
     */
    public SequencePair<K, V> createMergedSequence(SequencePair sequencePair1, SequencePair sequencePair2) {
        // Новая последовательность
        SequencePair resultSequencePair = new SequencePair();
        TreeMap<K, V> mapSequencePair1= sequencePair1.getMapSequencePair();
        TreeMap<K, V> mapSequencePair2= sequencePair2.getMapSequencePair();
        Map.Entry<K, V> firstEntry2 = mapSequencePair2.firstEntry();
        K startDt2 = firstEntry2.getKey();
        Map.Entry<K, V> lastEntry1 = mapSequencePair1.lastEntry();
        K endDt1 = lastEntry1.getKey();
        Map.Entry<K, V> lastEntry2 = mapSequencePair2.lastEntry();
        K endDt2 = lastEntry2.getKey();
        for (Map.Entry entry : mapSequencePair1.entrySet()) {
            K currentKey = (K) entry.getKey();
            V currentValue = (V) entry.getValue();
            if (currentKey.compareTo(startDt2) >= 0 && currentKey.compareTo(endDt1) <= 0) {
                resultSequencePair.addPairToSequence(currentKey, currentValue);
            }
        }
        for (Map.Entry entry : mapSequencePair2.entrySet()) {
            K currentKey = (K) entry.getKey();
            V currentValue = (V) entry.getValue();
            if (currentKey.compareTo(startDt2) >= 0 && currentKey.compareTo(endDt2) <= 0) {
                resultSequencePair.addPairToSequence(currentKey, currentValue);
            }
        }
        return resultSequencePair;
    }
}
