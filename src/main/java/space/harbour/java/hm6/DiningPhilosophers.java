package space.harbour.java.hm6;

public class DiningPhilosophers {
    static final int N = 5;

    public static class Philosopher extends Thread {
        private String name;
        private Object leftChopStick;
        private Object rightChopStick;

        public Philosopher(String name, Object leftChopStick, Object rightChopStick) {
            this.name = name;
            this.leftChopStick = leftChopStick;
            this.rightChopStick = rightChopStick;
        }

        public void think() {
            System.out.println(name + " is thinking...");
        }

        public void eat() {
            synchronized (leftChopStick) {
                System.out.println(name + " got left chopsticks");
                synchronized (rightChopStick) {
                    System.out.println(name + " is eating...");
                }
            }
        }

        @Override
        public void run() {
            while (true) {
                think();
                eat();
            }

        }
    }

    public static void main(String[] args) {
        Philosopher[] philosophers = new Philosopher[N];
        Object[] chopsticks = new Object[N];

        for (int i = 0; i < N; i++) {
            chopsticks[i] = new Object();
        }

        for (int i = 0; i < N; i++) {
            Object leftChop = chopsticks[i];
            Object rightChop = chopsticks[(i + 1) % N];
            if (i == 0) {
                philosophers[i] = new Philosopher("P" + (i + 1), leftChop, rightChop);
            } else {
                philosophers[i] = new Philosopher("P" + (i + 1), rightChop, leftChop);
            }
            philosophers[i].start();
        }

    }
}
