package demo.design.level.low.parkinglot.services.cache.implementations;

import demo.design.level.low.parkinglot.services.cache.ICacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CacheService implements ICacheService {

    private final InMemoryCacheService  inMemoryCacheService;

    @Autowired
    public CacheService() {
        this.inMemoryCacheService = new InMemoryCacheService();
    }

    @Override
    public <K, V> void put(final K key, final V value, final Long timeToLive) {
        this.inMemoryCacheService.put(key, value, timeToLive);
    }

    @Override
    public <K, V> V get(final K key) {
        return this.inMemoryCacheService.get(key);
    }
}
