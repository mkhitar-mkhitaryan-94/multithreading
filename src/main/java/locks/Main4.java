package locks;

import java.util.concurrent.locks.StampedLock;

public class Main4 {
    private volatile long itemsInChest = 0;
    private StampedLock stampedLock = new StampedLock();

    public static void main(String[] args) throws InterruptedException {
        new Main4().go();

    }

    private void go() throws InterruptedException {
        Thread t1 = new Thread(() -> lookForItems(1));
        Thread t2 = new Thread(() -> lookForItems(2));
        Thread t3 = new Thread(this::addItemsToSale);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

    }

    private void lookForItems(int id) {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                long stamp = stampedLock.tryOptimisticRead();
                long tmp = itemsInChest;
                if (!stampedLock.validate(stamp)) {
                    System.out.println("   id" + id + " protected value has been changed");
                    stamp = stampedLock.readLock();
                    System.out.println("  id" + id + " new readLock");
                    try {
                        tmp = itemsInChest;
                    } finally {
                        stampedLock.unlockRead(stamp);
                    }
                }
                System.out.println("   id" + id + " current value: " + tmp);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void addItemsToSale() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                long stamp = stampedLock.writeLock();
                try {
                    long tmp = itemsInChest;
                    System.out.println("start chest modification: " + tmp);
                    Thread.sleep(10000);
                    tmp++;
                    itemsInChest = tmp;
                    System.out.println("end chest modification: " + tmp);

                } finally {
                    stampedLock.unlockWrite(stamp);
                }
                Thread.sleep(30000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().isInterrupted();
        }
    }
}
