package semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreThread implements Runnable {
    Semaphore semaphore;
    int i;

    public SemaphoreThread(Semaphore semaphore, int i) {
        this.semaphore = semaphore;
        this.i = i;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread" + i + " is waiting for the semaphore");
            semaphore.acquire();
            System.out.println("Thread " + i + " acquired the semaphore");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Thread" + i + " released the semaphore");
            semaphore.release();
        }

    }
}
