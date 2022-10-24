package exchanger;

import java.util.concurrent.Exchanger;

public class Main {
    public static void main(String[] args) {
        Exchanger exchanger = new Exchanger();
        new Thread(new Producer(exchanger)).start();
        new Thread(new Consumer(exchanger)).start();

    }
}

class Producer implements Runnable {

    Exchanger<DataBuffer> exchanger;
    DataBuffer producerBuffer;

    public Producer(Exchanger exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        DataBuffer producerBuffer = new DataBuffer();
        for (int i = 0; i < 3; i++) {
            producerBuffer.addToBuffer("Producer " + i);
            if (producerBuffer.isFull()) {
                try {
                    producerBuffer = exchanger.exchange(producerBuffer);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

class Consumer implements Runnable {

    Exchanger<DataBuffer> exchanger;
    DataBuffer producerBuffer;

    public Consumer(Exchanger exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        DataBuffer consumerBuffer = new DataBuffer();
        for (int i = 0; i < 3; i++) {

            try {
                System.out.println("Waiting ....");
                consumerBuffer = exchanger.exchange(consumerBuffer);
                System.out.println("Received " + consumerBuffer.getData());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
