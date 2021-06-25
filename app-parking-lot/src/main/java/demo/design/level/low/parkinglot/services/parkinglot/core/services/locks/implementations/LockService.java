package demo.design.level.low.parkinglot.services.parkinglot.core.services.locks.implementations;

import demo.design.level.low.parkinglot.services.parkinglot.core.services.locks.ILockService;
import org.checkerframework.checker.units.qual.C;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockService<K, L> implements ILockService<K, L> {

    private final Map<K, L> locks = new ConcurrentHashMap<>();
    private final Class<L> lockClass;

    public LockService(final Class<L> lockClass) {
        this.lockClass = lockClass;
    }

    @Override
    public L getLock(final K key)  {
        var lock = this.locks.get(key);
        if (lock == null) {
            synchronized (key) {
                lock = this.locks.get(key);
                if (lock == null) {
                    this.locks.put(key, lock = this.getNewLock());
                }
            }
        }
        return (L) lock;
    }

    private L getNewLock() {
        L lock = null;
        if (ReentrantLock.class.isAssignableFrom(this.lockClass)) {
            lock = (L) new ReentrantLock();
        } else if (ReentrantReadWriteLock.class.isAssignableFrom(ReentrantReadWriteLock.class)) {
            lock = (L) new ReentrantReadWriteLock();
        }
        return lock;
    }
}
