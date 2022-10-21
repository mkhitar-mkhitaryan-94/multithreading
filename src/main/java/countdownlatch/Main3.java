package countdownlatch;

import java.util.concurrent.CountDownLatch;

public class Main3 {
    public static void main(String[] args) {
        int keyAmount = 5;
        CountDownLatch door = new CountDownLatch(keyAmount);
        new Thread(new KeyBringer(door,keyAmount)).start();
        try {
            door.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Door has been opened!");
    }
}
