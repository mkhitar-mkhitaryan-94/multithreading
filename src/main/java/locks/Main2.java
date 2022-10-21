package locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main2 {
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();
    public static char currentChar = 'A';

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    lock.lock();
                    while (currentChar != 'A') {
                        condition.await();
                    }
                    System.out.println('A');
                    currentChar = 'B';
                    condition.signal();
                    lock.unlock();

                }
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    lock.lock();
                    while (currentChar != 'B') {
                        condition.await();
                    }
                    System.out.println('B');
                    currentChar = 'C';
                    condition.signal();
                    lock.unlock();

                }
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    lock.lock();
                    while (currentChar != 'C') {
                        condition.await();
                    }
                    System.out.println('C');
                    currentChar = 'A';
                    condition.signal();
                    lock.unlock();

                }
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }).start();

    }
}
