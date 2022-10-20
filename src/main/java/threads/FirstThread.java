package threads;

import java.util.List;

public class FirstThread extends Thread {
    public FirstThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        List<Integer> list = ThreadMain.integers;
        int sum = 0;
        for (int element : list) {
            sum += element;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        System.out.println(Thread.currentThread().getName());
        System.out.println("sum = " + sum);
    }
}
