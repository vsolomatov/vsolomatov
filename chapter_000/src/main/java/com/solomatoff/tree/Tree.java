package com.solomatoff.tree;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;

    /**
     *  Конструктор объектов нашего класса. Создает только корневой элемент дерева. Остальное построение должно происходить с помощью метода add
     * @param rootValue значение корневого элемента дерева.
     */
    Tree(E rootValue) {
        this.root = new Node<>(rootValue);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = true;
        Optional<Node<E>> optionalNodeParent = findBy(parent);
        try {
            Node<E> nodeParent = optionalNodeParent.get();
            Node<E> newChild = new Node<>(child);
            nodeParent.add(newChild);
        } catch (NoSuchElementException nsee) {
            result = false;
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Queue<Node<E>> data = new LinkedList<>();
            Boolean hasNext = null;

            @Override
            public boolean hasNext() {
                if (this.hasNext == null) {
                    Queue<Node<E>> tempData = new LinkedList<>();
                    tempData.offer(root);
                    Node<E> el;
                    while (!tempData.isEmpty()) {
                        el = tempData.poll();
                        if (el != null) {
                            data.offer(el);
                            for (Node<E> child : el.leaves()) {
                                tempData.offer(child);
                            }
                        }
                    }
                }
                this.hasNext = !this.data.isEmpty();
                return this.hasNext;
            }

            @Override
            public E next() throws NoSuchElementException {
                E result;
                if (this.hasNext == null) {
                    hasNext();
                }
                if (this.hasNext) {
                    result = data.poll().getValue();
                } else {
                    throw new NoSuchElementException();
                }
                this.hasNext = !this.data.isEmpty();
                return result;
            }
        };
    }
}

interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * Добавить элемент child в parent.
     * Parent может иметь список child.
     * @param parent parent.
     * @param child child.
     * @return в случае успеха - true, иначе - false
     */
    boolean add(E parent, E child);

    Optional<Node<E>> findBy(E value);
}

/**
 * @author Vyacheslav Solomatov (solomatoff.vaycheslav@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
class Node<E extends Comparable<E>> {
    private final List<Node<E>> children = new ArrayList<>();
    private final E value;

    public Node(final E value) {
        this.value = value;
    }

    public void add(Node<E> child) {
        this.children.add(child);
    }

    public List<Node<E>> leaves() {
        return this.children;
    }

    public E getValue() {
        return this.value;
    }

    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;

    }
}

