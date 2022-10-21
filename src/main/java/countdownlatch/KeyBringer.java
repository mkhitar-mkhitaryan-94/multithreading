package countdownlatch;

import java.util.concurrent.CountDownLatch;

public class KeyBringer implements Runnable {
    CountDownLatch countDownLatch;
    int counter;

    public KeyBringer(CountDownLatch countDownLatch, int counter) {
        this.countDownLatch = countDownLatch;
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < counter; i++) {
            System.out.println("Key " + i + " inserted");
            countDownLatch.countDown();
            try {
                Thread.sleep(500 + (int) Math.random() * 500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
