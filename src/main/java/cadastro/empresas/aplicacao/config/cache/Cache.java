package cadastro.empresas.aplicacao.config.cache;

import java.lang.reflect.Method;

import cadastro.empresas.aplicacao.interceptor.cache.annotations.Cacheable;

public class Cache {

	private String name;
	private String key;
	
	private Cache() {
	}
	
	private Cache(String name, String key) {
		this.name = name;
		this.key = key;
	}
	
	public static Cache createFromMethod(Method method) {
		if(method.isAnnotationPresent(Cacheable.class)) {
			return createFromCacheableAnnotation(method.getDeclaredAnnotation(Cacheable.class));
		}
		return new Cache("", "");
	}
	
	private static Cache createFromCacheableAnnotation(Cacheable cacheableAnnotation) {
		String cacheName = cacheableAnnotation.name();
		String cacheKey = cacheableAnnotation.key().substring(1);
		return new Cache(cacheName, cacheKey);
	}
	
	public void updateCacheName(String key, Object value) {
		this.name += String.format("#%s:%s",key, value);
	}

	public String getName() {
		return name;
	}

	public String getKey() {
		return key;
	}
}

