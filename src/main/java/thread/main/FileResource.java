package thread.main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class FileResource {
    private FileWriter writer;

    public FileResource(String fileName) throws IOException {
        writer = new FileWriter(fileName, true);

    }

    public synchronized void writeString(int n) {
        try {
            writer.write(Thread.currentThread().getName() + ":Start:  #" + n);
            System.out.println(Thread.currentThread().getName() + ":Start:  #" + n);
            TimeUnit.SECONDS.sleep(1);
            writer.write(Thread.currentThread().getName() + ":End:  #" + n);
            System.out.println(Thread.currentThread().getName() + ":End:  #" + n);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
