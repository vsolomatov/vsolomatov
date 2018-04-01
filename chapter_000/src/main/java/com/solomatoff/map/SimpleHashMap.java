package com.solomatoff.map;

import org.jetbrains.annotations.NotNull;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  Класс реализует собственную структуру данных - HashMap
 * @param <K> тип ключей
 * @param <V> тип значений
 */
public class SimpleHashMap<K, V> implements Iterator<K> {
    class Pair<K, V> {
        K key;
        V value;
        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    private int currCapacity = 10;
    private Boolean hasNext = null;
    private int indexNext = -1;
    @SuppressWarnings("unchecked")
    private Pair<K, V>[] table = new Pair[currCapacity];

    /**
     *  Метод формирует индекс элемента в хэш-таблице.
     * @param key элемент хэш-таблицы.
     * @param capacity емкость хэш-таблицы (количество элементов массива)
     * @return индекс элемента в хэш-таблице.
     */
    private int hashCodeMaker(@NotNull K key, int capacity) {
        return Math.abs(key.hashCode() % capacity);
    }

    /**
     *  Метод добавляет элемент в наше отображение.
     * @param key - ключ
     * @param value - значение
     * @return истину, если добавление произошло, и ложь - в противном случае
     */
    boolean insert(K key, V value) {
        boolean result = true;
        int index = hashCodeMaker(key, this.currCapacity);
        int newCapacity;
        if (this.table[index] != null) {
            if (!this.table[index].key.equals(key)) {
                newCapacity = this.currCapacity * 3;
                @SuppressWarnings("unchecked")
                Pair<K, V>[] newTable = new Pair[newCapacity];
                int newIndex;
                int tempIndex = nextNoEmptyElement(0);
                while (tempIndex != -1) {
                    newIndex = hashCodeMaker(this.table[tempIndex].key, newCapacity);
                    newTable[newIndex] = this.table[tempIndex];
                    tempIndex = nextNoEmptyElement(tempIndex + 1);
                }
                this.table = newTable;
                this.currCapacity = newCapacity;
                newIndex = hashCodeMaker(key, this.currCapacity);
                // в данном случае возможна коллизия и элемент просто перезапишется
                this.table[newIndex] = new Pair<>(key, value);
            } else {
                result = false;
            }
        } else {
            this.table[index] = new Pair<>(key, value);
        }
        return result;
    }

    /**
     *  Метод возвращает значение по ключу из нашего отображения.
     * @param key - ключ
     * @return если key найдет, то значение по ключу из нашего отображения, в противном случае - null
     */
    V get(K key) {
        V result;
        int index = hashCodeMaker(key, this.currCapacity);
        if (this.table[index] != null && this.table[index].key.equals(key)) {
            result = this.table[index].value;
        } else {
            result = null;
        }
        return result;
    }

    /**
     *  Метод удаляет значение по ключу из нашего отображения.
     * @param key - ключ
     * @return истину, если удаление произошло, и ложь - в противном случае
     */
    boolean delete(K key) {
        boolean result;
        int index = hashCodeMaker(key, this.currCapacity);
        if ((this.table[index] != null) && (this.table[index].key.equals(key))) {
            this.table[index] = null;
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public boolean hasNext() {
        if (this.hasNext == null) {
            this.indexNext = nextNoEmptyElement(0);
            this.hasNext = (this.indexNext >= 0);
        }
        return this.hasNext;
    }

    @Override
    public K next() throws NoSuchElementException {
        K result;
        if (this.hasNext == null) {
            hasNext();
        }
        if (this.hasNext && (this.indexNext >= 0)) {
            result = this.table[indexNext].key;
        } else {
            throw new NoSuchElementException();
        }
        this.indexNext = nextNoEmptyElement(this.indexNext + 1);
        this.hasNext = (this.indexNext >= 0);
        return result;
    }

    /**
     *  Метод возвращает индекс ближайшего непустого элемента массива table, начиная с позиции beginIndex
     * @param beginIndex начальная позиция поиска
     * @return индекс ближайшего непустого элемента массива, если такой имеется, иначе возвращает -1
     */
    private int nextNoEmptyElement(int beginIndex) {
        int result = -1;
        for (int index = beginIndex; index < this.table.length; index++) {
            if (table[index] != null) {
                result = index;
                break;
            }
        }
        return result;
    }

    /* public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        String ln = System.getProperty("line.separator");
        for (int i = 0; i < table.length; i++) {
            if (this.table[i] != null) {
                stringBuilder.append("i = ").append(i).append(" : ");
                stringBuilder.append("<" + this.table[i].key + "> ").append("<" + this.table[i].value + ">");
                stringBuilder.append(ln);
            }
        }
        return stringBuilder.toString();
    }*/
}
