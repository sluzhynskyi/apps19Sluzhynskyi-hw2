package ua.edu.ucu.collections.immutable;

import java.util.Arrays;

public class ImmutableLinkedList implements ImmutableList {
    private Node head;

    public ImmutableLinkedList() {
    }

    public ImmutableLinkedList copy(int start, int end) {
        ImmutableLinkedList res = new ImmutableLinkedList();
        res.head = new Node(head.data);
        Node currentRes = res.head;
        Node current = head;
        for (int i = start; i < end - 1; i++) {
            if (current.getNext() != null) {
                current = current.getNext();
                currentRes.setNext(new Node(current.getData()));
                currentRes = currentRes.getNext();
            }
        }
        return res;
    }
    public ImmutableLinkedList addFirst(Object e){
        return add(0,e);
    }
    public ImmutableLinkedList addLast(Object e){
        return add(e);
    }
    public Object getFirst(){
        return get(0);
    }
    public Object getLast(){
        return get(size());
    }
    public ImmutableLinkedList removeFirst(){
        return remove(0);
    }
    public ImmutableLinkedList removeLast(){
        return remove(size());
    }

    @Override
    public ImmutableLinkedList add(Object e) {
        return add(size(), e);
    }

    @Override
    public int size() {
        int counter = 0;
        Node current = head;
        if (current != null) {
            counter++;
            while (current.getNext() != null) {
                counter++;
                current = current.getNext();
            }
        }
        return counter;
    }


    @Override
    public ImmutableLinkedList add(int index, Object e) {
        return addAll(index, new Object[]{e});
    }
//
//    public static void main(String[] args) {
//        ImmutableLinkedList im1 = new ImmutableLinkedList();
//        ImmutableLinkedList im2 = im1.add(123);
//        ImmutableLinkedList im3 = im2.add(321);
//        ImmutableLinkedList im4 = im3.add("aesf");
//        ImmutableLinkedList im5 = im4.add(1, "wow");
//        ImmutableLinkedList im6 = im5.add("I'm last");
//        ImmutableLinkedList im7 = im6.addAll(3, new Object[]{"H", "e", "l", "p"});
//        ImmutableLinkedList im8 = im7.addAll(new Object[]{"A", "d", "d", "A", "l", "l"});
//        ImmutableLinkedList im9 = im8.remove(1);
//        ImmutableLinkedList im10 = im9.set(1, "changed");
//
//        System.out.println(im1);
//        System.out.println(im2);
//        System.out.println(im3);
//        System.out.println(im4);
//        System.out.println(im5);
//        System.out.println(im6);
//        System.out.println(im7);
//        System.out.println(im8);
//        System.out.println(im9);
//        System.out.println(im10);
//        System.out.println(im10.indexOf("H"));
//        System.out.println(Arrays.toString(im10.toArray()));
//
//    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        return addAll(size(), c);
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        ImmutableLinkedList res = new ImmutableLinkedList();
        if (head == null) {
            res.head = new Node(c[0]);
            Node currentRes = res.head;
            for (int i = 1; i < c.length; i++) {
                currentRes.setNext(new Node(c[i]));
                currentRes = currentRes.getNext();
            }
        } else {
            res = copy(0, index);
            Node currentRes = res.last();
            for (int i = 0; i < c.length; i++) {
                currentRes.setNext(new Node(c[i]));
                currentRes = currentRes.getNext();
            }
            Node current = nodeByIndex(index - 1);

            while (current.getNext() != null) {
                current = current.getNext();
                currentRes.setNext(new Node(current.getData()));
                currentRes = currentRes.getNext();
            }
        }
        return res;

    }

    @Override
    public Object get(int index) {
        return nodeByIndex(index).data;
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        ImmutableLinkedList res = copy(0, index);
        Node currentRes = res.last();
        Node current = nodeByIndex(index);
        while (current.getNext() != null) {
            current = current.getNext();
            currentRes.setNext(new Node(current.getData()));
            currentRes = currentRes.getNext();
        }
        return res;
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        ImmutableLinkedList res = remove(index);
        return res.add(index, e);
    }

    @Override
    public int indexOf(Object e) {
        Node current = head;
        if (current != null) {
            for (int i = 0; i < size(); i++) {
                if (current.getData().equals(e)) {
                    return i;
                }
                current = current.getNext();
            }
        }
        return -1; // Means Error
    }


    @Override
    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Object[] toArray() {
        Object[] res = new Object[size()];
        Node current = head;
        for (int i = 0; i < size(); i++) {
            res[i] = current.getData();
            current = current.getNext();
        }
        return res;
    }

    @Override
    public String toString() {
        StringBuilder all_nodes = new StringBuilder();
        Node current = head;
        if (current != null) {
            all_nodes.append(head.data.toString()).append(", ");
            while (current.getNext() != null) {
                current = current.getNext();
                all_nodes.append(current.data.toString()).append(", ");
            }
        }
        return "ImmutableLinkedList["
                + all_nodes +
                ']';
    }
    // All what is connected to Node
    public Node last() {
        return nodeByIndex(size());
    }

    public Node nodeByIndex(int index) {
        Node current = head;
        if (current != null) {
            for (int i = 0; i < index; i++) {
                if (current.getNext() != null) {
                    current = current.getNext();
                }
            }
        }
        return current;
    }
    private static class Node {
        Node next;
        final Object data;

        private Node(Object data) {
            this.data = data;
            this.next = null;
        }

        private Node(Object data, Node nextNode) {
            this.data = data;
            this.next = nextNode;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getNext() {
            return next;
        }

        public Object getData() {
            return data;
        }
    }
}
