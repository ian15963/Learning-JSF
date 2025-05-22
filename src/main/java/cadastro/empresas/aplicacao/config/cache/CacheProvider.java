package cadastro.empresas.aplicacao.config.cache;

import java.time.Duration;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import org.ehcache.Cache;
import org.ehcache.Cache.Entry;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

@ApplicationScoped
public class CacheProvider {

	private CacheManager cacheManager;
	private Cache<String, Object> cache;
	
	@PostConstruct
	public void init() {
		cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
				.withCache("cache", 
					CacheConfigurationBuilder.newCacheConfigurationBuilder(
							String.class,
							Object.class,
							ResourcePoolsBuilder.heap(100)
					)
					.withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(15)))
				)
				.build();
		
		cacheManager.init();
		cache = cacheManager.getCache("cache", String.class, Object.class);
	}
	
	public void put(String key, Object value) {
	   cache.put(key, value);
	}

	public Object get(String key) {
	    return cache.get(key);
	}
	
	public void remove(String key) {
		cache.remove(key);
	}

	public boolean contains(String key) {
	    return cache.containsKey(key);
	}
	
	public void removeAll(String key) {
		Iterator<Entry<String, Object>> iterator = cache.iterator();
		while(iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			String entryKey = entry.getKey();
			if(entryKey.contains(key)) {
				cache.remove(entryKey);
			}
		}
	}
	
	@PreDestroy
	public void shutdown() {
	    cacheManager.close();
	}
	
}
