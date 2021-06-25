package demo.design.level.low.parkinglot.services.cache.implementations;

import demo.design.level.low.parkinglot.services.cache.ICacheService;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class InMemoryCacheService implements ICacheService {

    @Getter
    private final class CacheEntry<V> {
        private final V value;
        private final Long expirationTime;
        public CacheEntry(final V value, final Long timeToLive) {
            this.value = value;
            if (timeToLive == null) {
                this.expirationTime = null;
            } else {
                this.expirationTime = System.currentTimeMillis() + timeToLive;
            }
        }
        public boolean isExpired() {
            return this.expirationTime != null && System.currentTimeMillis() > this.expirationTime;
        }
    }

    private final Map<Object, CacheEntry> cache = new ConcurrentHashMap<>();

    @Override
    public final <K, V> void put(final K key, final V value, final Long timeToLive) {
        this.cache.put(key, new CacheEntry<>(value, timeToLive));
    }

    @Override
    public final <K, V> V get(final K key) {
        V value = null;
        CacheEntry<V> cacheEntry = this.cache.get(key);
        if (cacheEntry != null) {
            if (cacheEntry.isExpired()) {
                this.evict(key); // Eviction when fetched expired entry
            } else {
                value = cacheEntry.getValue();
            }
        }
        return value;
    }

    private final <K> void evict(final K key) {
        this.cache.remove(key);
    }

}


