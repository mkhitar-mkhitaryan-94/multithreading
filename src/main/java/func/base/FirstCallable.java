package func.base;

import java.util.List;
import java.util.concurrent.Callable;

public class FirstCallable implements Callable<Integer> {
    @Override
    public Integer call()  {
        List<Integer> list = ThreadMain.integers;
        int sum = 0;
        for (int element : list) {
            sum += element + 1;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(Thread.currentThread().getName());
        System.out.println("sum = " + sum);
        return sum;
    }
    }

