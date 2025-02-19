package roborace.common;

import java.util.LinkedList;
import java.util.List;

public class Queue<E> {

    private final List<E> data;

    public Queue() {
        data = new LinkedList<E>();
    }

    public synchronized void push(E element) {
        data.add(element);
        notify();
    }

    public synchronized E pull()  {
        while (data.isEmpty()) {
            try { 
                wait(); 
            } catch (InterruptedException e) {}
        }
        return data.remove(0);
    }

    public void close() {
    }	
}