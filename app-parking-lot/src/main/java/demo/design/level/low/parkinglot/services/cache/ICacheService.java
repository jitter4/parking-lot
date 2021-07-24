package demo.design.level.low.parkinglot.services.cache;

public interface ICacheService {

    <K, V> void put(K key, V value, Long timeToLive);
    <K, V> V get(K key);

}
