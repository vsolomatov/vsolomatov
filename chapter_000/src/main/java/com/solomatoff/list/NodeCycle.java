package com.solomatoff.list;

public class NodeCycle<T> {
    boolean hasCycle(Node<T> root) {
        if (root == null) {
            return false;
        }
        boolean result = false;
        Node<T> first = root;
        Node<T> second = root;
        while (first.next != null) {
            // делаем первый шаг используя первый указатель
            first = first.next;
            if (first.next == null) {
                result = false;
                break;
            }
            // делаем второй шаг используя первый указатель
            first = first.next;

            if (first == second) {
                result = true;
                break;
            }
            // делаем первый шаг используя второй указатель
            second = second.next;
        }
        return result;
    }
}
class Node<T> {
    private T value;
    Node<T> next;

    public Node(T value) {
        this.value = value;
    }
}