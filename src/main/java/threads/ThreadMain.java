package threads;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ThreadMain {
    static List<Integer> integers = IntStream.range(1, 100)
            .boxed()
            .collect(Collectors.toList());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FirstThread thread = new FirstThread("Integers sum");
        // thread.start();
        Thread thread1 = new Thread(new FirstRunnable());
        // thread1.start();
        ExecutorService service = Executors.newSingleThreadExecutor();
        FirstCallable thread2 = new FirstCallable();
        Future<Integer> integerFuture = service.submit(thread2);
        int result = integerFuture.get();
        System.out.println("main result" + result);
        service.shutdown();
        System.out.println("end of main" + Thread.currentThread().getName());
    }
}
