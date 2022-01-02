import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyReadWriteLock {
    public static void main(String[] args) {
        Resource resource = new Resource();
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                resource.put(Thread.currentThread().getName(), Thread.currentThread().getName());
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                resource.get(Thread.currentThread().getName());
            }, String.valueOf(i)).start();
        }
    }
}

class Resource{
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private volatile Map<String, Object> resourceMap = new HashMap<>();

    public void put(String key, Object val){
        readWriteLock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + " is writing");
            resourceMap.put(key, val);
            System.out.println(Thread.currentThread().getName() + " finish writing");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public Object get(String key){
        readWriteLock.readLock().lock();

        Object o = null;
        try {
            System.out.println(Thread.currentThread().getName() + " is getting");
            o = resourceMap.get(key);
            System.out.println(Thread.currentThread().getName() + " finish getting");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
            return o;
        }

    }
}