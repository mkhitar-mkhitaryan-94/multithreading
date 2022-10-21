package locks;

import java.util.concurrent.locks.Lock;

public class Counter {

    private Integer counter = 0;
    private Lock lock;

    public Counter() {

    }

    public Counter(Lock lock) {
        this.lock = lock;
    }

    public void inc() {
        lock.lock();
        this.counter++;
        lock.unlock();
    }

    public void dec() {
        lock.lock();
        this.counter--;
        lock.unlock();
    }

    public Integer getCounter(){
        return this.counter;
    }
}
