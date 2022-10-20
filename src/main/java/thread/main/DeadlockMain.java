package thread.main;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DeadlockMain {
    public static void main(String[] args) throws IOException {
        FileResource resA = new FileResource("str.txt");
        FileResource resB = new FileResource("str.txt");
        LockThread thread1 = new LockThread(resA,resB);
        LockThread thread2 = new LockThread(resB,resA);
        thread1.start();
        thread2.start();

    }
}

class LockThread extends Thread {
    private FileResource resA;
    private FileResource resB;

    public LockThread(FileResource resA, FileResource resB) {
        this.resA = resA;
        this.resB = resB;
    }

    @Override
    public void run() {
        synchronized (resA) {
            System.out.println("First block");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (resB) {
                System.out.println("Second block");

            }
        }
    }
}
