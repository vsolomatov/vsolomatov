package com.solomatoff.tree;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Класс реализует примитивное двоичное дерево поиска.
 * @param <E> тип хранимых в дереве значений.
 * @author Vyacheslav Solomatov (solomatoff.vaycheslav@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BinarySearchTree<E extends Comparable<E>> implements Iterable<E> {
    private BinaryTreeNode<E> root = null;

    /**
     * Метод добавлеят значение  valueNewNode в нужное место дерева.
     * @param valueNewNode значение, добавляемое в дерево.
     */
    public void add(E valueNewNode) {
        if (this.root == null) {
            this.root = new BinaryTreeNode(valueNewNode);
        } else {
            addTo(this.root, valueNewNode);
        }
    }

    /**
     * Метод добавлеят значение  valueNewNode в нужное место дерева, начиная поиск с указанного узла
     * @param node начальный узел поиска
     * @param valueNewNode вставляемое значение
     */
    private void addTo(BinaryTreeNode<E> node, E valueNewNode) {
        if (valueNewNode.compareTo(node.getValue()) <= 0) {
            if (node.getLeft() == null) {
                node.setLeft(new BinaryTreeNode(valueNewNode));
            } else {
                node = node.getLeft();
                addTo(node, valueNewNode);
            }
        } else {
            if (node.getRight() == null) {
                node.setRight(new BinaryTreeNode(valueNewNode));
            } else {
                node = node.getRight();
                addTo(node, valueNewNode);
            }
        }
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Boolean hasNext = null;
            Queue<BinaryTreeNode<E>> data = new LinkedList<>();

            @Override
            public boolean hasNext() {
                if (hasNext == null) {
                    Queue<BinaryTreeNode<E>> tempData = new LinkedList<>();
                    tempData.offer(root);
                    BinaryTreeNode<E> el;
                    while (!tempData.isEmpty()) {
                        el = tempData.poll();
                        if (el != null) {
                            data.offer(el);
                            if (el.getLeft() != null) {
                                tempData.offer(el.getLeft());
                            }
                            if (el.getRight() != null) {
                                tempData.offer(el.getRight());
                            }
                        }
                    }
                }
                hasNext = !data.isEmpty();
                return hasNext;
            }

            @Override
            public E next() throws NoSuchElementException {
                E result;
                if (hasNext == null) {
                    hasNext();
                }
                if (hasNext) {
                    BinaryTreeNode<E> temp = data.poll();
                    result = temp.getValue();
                } else {
                    throw new NoSuchElementException();
                }
                hasNext = !data.isEmpty();
                return result;
            }
        };
    }
}

/**
 * Класс реализует узел дерева.
 * @param <E> тип хранимых в дереве значений.
 * @author Vyacheslav Solomatov (solomatoff.vaycheslav@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
class BinaryTreeNode<E extends Comparable<E>> {
    private BinaryTreeNode<E> left;
    private BinaryTreeNode<E> right;
    private final E value;

    BinaryTreeNode(E value) {
        this.left = null;
        this.right = null;
        this.value = value;
    }

    E getValue() {
        return this.value;
    }

    BinaryTreeNode<E> getLeft() {
        return this.left;
    }

    BinaryTreeNode<E> getRight() {
        return this.right;
    }

    void setLeft(BinaryTreeNode<E> newLeft) {
        this.left = newLeft;
    }

    void setRight(BinaryTreeNode<E> newRight) {
        this.right = newRight;
    }
}

