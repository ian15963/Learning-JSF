package cadastro.empresas.aplicacao.config;

import java.time.Duration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;

import org.apache.commons.collections4.map.PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy;
import org.apache.commons.collections4.map.PassiveExpiringMap.ExpirationPolicy;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Expirations;
import org.ehcache.expiry.ExpiryPolicy;

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

	    public boolean contains(String key) {
	        return cache.containsKey(key);
	    }

	    @PreDestroy
	    public void shutdown() {
	        cacheManager.close();
	    }
	
}
