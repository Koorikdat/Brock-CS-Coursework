package COSC3P91.util;

import java.util.LinkedList;

public class ThreadPool extends ThreadGroup {

    private boolean isAlive;
    private final LinkedList<Runnable> taskQueue;		
    private int threadID;
    private static int threadPoolID;

    public ThreadPool(int numThreads) {
        super("ThreadPool-" + (threadPoolID++));
        setDaemon(true);
        isAlive = true;
        taskQueue = new LinkedList<Runnable>();		
        startThreads(numThreads);
    }

    public synchronized void runTask(Runnable task) {
        if (!isAlive) {
            throw new IllegalStateException();
        }
        if (task != null) {
            taskQueue.add(task);
            notify();
        }
    }

    protected synchronized Runnable getTask() throws InterruptedException {
        Runnable result = null;
        while (taskQueue.isEmpty() && isAlive) {
            wait();
        }
        if (isAlive) {
            result = taskQueue.removeFirst();	
        }
        return result;			
    }

    public synchronized void close() {
        if (isAlive) {
            isAlive = false;
            taskQueue.clear();
            interrupt();
        }
    }

    public void join() {
        synchronized (this) {
            isAlive = false;
            notifyAll();
        }
        Thread[] threads = new Thread[activeCount()];
        int count = enumerate(threads);
        for (int i=0; i<count; i++) {
            try {
                threads[i].join();
            }
            catch (InterruptedException ex) {}
        }
    }

    protected void threadStarted() {
    }

    protected void threadStopped() {
    }
    
    private void startThreads(int numThreads) {
        for (int i=0; i<numThreads; i++) {
            new PooledThread().start();
        }
    }

    private class PooledThread extends Thread {

        public PooledThread() {
            super(ThreadPool.this,"PooledThread-" + (threadID++));
        }

        @Override
        public void run() {
            threadStarted();
            while (!isInterrupted()) {
                Runnable task = null;
                try {
                    task = getTask();
                }
                catch (InterruptedException ex) {}
                if (task != null) {
                    try {
                        task.run();
                    }
                    catch (Throwable t) {
                        uncaughtException(this, t);
                    }
                } 
            }
            threadStopped();
        }
    }
}