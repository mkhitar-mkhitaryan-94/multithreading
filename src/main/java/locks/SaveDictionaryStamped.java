package locks;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.StampedLock;

public class SaveDictionaryStamped {
    private final Map<String, String> map = new TreeMap<>();
    private final StampedLock stampedLock = new StampedLock();

    public String get(String key) {
        long stamp = stampedLock.readLock();
        try {
            return map.get(key);
        } finally {
            stampedLock.unlock(stamp);
        }

    }

    public String[] getAll() {
        long stamp = stampedLock.readLock();
        try {
            return map.keySet().toArray(new String[0]);
        } finally {
            stampedLock.unlock(stamp);
        }
    }

    public String put(String key, String value) {
        long stamp = stampedLock.writeLock();
        try {
            return map.put(key, value);
        } finally {
            stampedLock.unlock(stamp);
        }
    }

    public void clear() {
        long stamp = stampedLock.writeLock();
        try {
            map.clear();
        } finally {
            stampedLock.unlock(stamp);
        }
    }
}
