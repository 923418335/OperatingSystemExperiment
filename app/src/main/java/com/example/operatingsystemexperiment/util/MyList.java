package com.example.operatingsystemexperiment.util;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by 山东娃 on 2016/3/14.
 */
public class MyList<E> implements Collection<E> ,List<E>{
    private int size;
    private Node<E> head;
    private Node<E> tail;

    public MyList() {
        size = 0;
        head = new Node<>();
        head.next = null;
        tail = head;
    }

    @Override
    public boolean add(E object) {
        Node<E> p = new Node<>();
        p.data = object;
        tail.next = p;
        tail = tail.next;
        tail.next = null;
        size++;
        Log.i("fuck", "add: " + toString());
        return true;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node<E> node = head.next;
        while(node != null){
            builder.append(node.data.toString());
            node = node.next;
        }
        return builder.toString();
    }


    @Override
    public boolean addAll(Collection<? extends E> collection) {
        Iterator<? extends E> iterator = collection.iterator();
        while(iterator.hasNext()){
            add(iterator.next());
        }
        return true;
    }


    @Override
    public void clear() {
        size = 0;
        head.next = null;
        tail = head;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @NonNull
    @Override
    public Iterator<E> iterator() {
        return new MyIterator<>();
    }

    class MyIterator<E> implements Iterator<E>{
        private Node<E> p;

        public MyIterator() {
            p = (Node<E>) head.next;
        }

        @Override
        public boolean hasNext() {
            return p.next != null;
        }

        @Override
        public E next() {
            E data = p.data;
            p = p.next;
            return data;
        }

        @Override
        public void remove() {

        }
    }


    @Override
    public E remove(int location) {
        int currentIndex = 0;
        Node<E> p = head.next;
        Node<E> pre = head;
        while(p != null && currentIndex != location){
            p = p.next;
            pre = pre.next;
            currentIndex++;
        }
        if(p == null){
            return null;
        }
        E e = p.data;
        if(p.next == null){
            tail = pre;
            pre.next = null;
        }else{
            pre.next = p.next;
        }
        size--;
        return e;
    }

    @Override
    public boolean remove(Object object) {
        Node<E> p = head.next;
        Node<E> pre = head;
        while(p != null && !p.data.equals(object)){
            p = p.next;
            pre = pre.next;
        }
        if(p == null){
            return false;
        }
        if(p.next == null){
            tail = pre;
            pre.next = null;
        }else{
            pre.next = p.next;
        }
        size--;
        return true;
    }



    @Override
    public void add(int location, E object) {

    }

    @Override
    public E get(int location) {
        int currentIndex = 0;
        Node<E> p = head.next;
        Log.i("fuck","head。next" + head.next);
        while(p != null && currentIndex != location){
            Log.i("fuck", String.valueOf(currentIndex));
            p = p.next;
            currentIndex++;
        }
        if(currentIndex == location){
            return p.data;
        }
        return null;

    }


    @Override
    public boolean addAll(int location, Collection<? extends E> collection) {
        return false;
    }




    @Override
    public boolean contains(Object object) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean equals(Object object) {
        return false;
    }


    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public int indexOf(Object object) {
        return 0;
    }



    @Override
    public int lastIndexOf(Object object) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @NonNull
    @Override
    public ListIterator<E> listIterator(int location) {
        return null;
    }


    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public E set(int location, E object) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @NonNull
    @Override
    public List<E> subList(int start, int end) {
        return null;
    }

    @NonNull
    @Override
    public Object[] toArray() {
        Object[] data =  new Object[size];
        Node<E> pNode = head.next;
        int index = 0;
        while(pNode != null){
            data[index++] = pNode.data;
            pNode = pNode.next;
        }
        return data;
    }

    @NonNull
    @Override
    public <T> T[] toArray(T[] array) {
        return null;
    }
    class Node<E>{
        public E data;
        public Node<E> next;

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }
}
