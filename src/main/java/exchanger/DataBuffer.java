package exchanger;

import java.util.ArrayList;
import java.util.List;

public class DataBuffer {
    private List<String> data = new ArrayList<>();

    public String getData() {
        return data.remove(0);
    }

    public void addToBuffer(String str) {
        data.add(str);
    }

    public boolean isFull() {
        return data.size() == 1;
    }
}
