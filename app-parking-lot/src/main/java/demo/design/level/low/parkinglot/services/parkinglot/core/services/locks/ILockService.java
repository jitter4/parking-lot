package demo.design.level.low.parkinglot.services.parkinglot.core.services.locks;

public interface ILockService<K, L> {

    L getLock(K key);

}
