package space.harbour.java.hm6;

import java.util.concurrent.atomic.AtomicInteger;

public class Concurrency {
    static final Integer bigN = 100_000;
    static AtomicInteger counter = new AtomicInteger(0);

    public static class MyThread extends Thread {
        @Override
        public void run() {
            super.run();

            for (int i = 0; i < bigN; i++) {
                System.out.println(getName() + " says: " + counter.incrementAndGet());
            }
        }
    }

    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        thread1.setName("T1");
        thread1.start();

        MyThread thread2 = new MyThread();
        thread2.setName("T2");
        thread2.start();
    }
}
