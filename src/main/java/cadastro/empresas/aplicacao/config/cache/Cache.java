package cadastro.empresas.aplicacao.config.cache;

import java.lang.reflect.Method;

import cadastro.empresas.aplicacao.interceptor.cache.annotations.CacheEvict;
import cadastro.empresas.aplicacao.interceptor.cache.annotations.CachePut;
import cadastro.empresas.aplicacao.interceptor.cache.annotations.Cacheable;

public class Cache {

	private String name;
	private String key;
	
	private Cache() {
	}
	
	private Cache(String name) {
		this.name = name;
	}
	
	private Cache(String name, String key) {
		this.name = name;
		this.key = key;
	}
	
	public static Cache createFromMethod(Method method) {
		if(method.isAnnotationPresent(Cacheable.class)) {
			return createFromCacheableAnnotation(method.getDeclaredAnnotation(Cacheable.class));
		}else if(method.isAnnotationPresent(CachePut.class)) {
			return createFromCachePutAnnotation(method.getDeclaredAnnotation(CachePut.class));
		}else if(method.isAnnotationPresent(CacheEvict.class)) {
			return createFromCacheEvitAnnotation(method.getDeclaredAnnotation(CacheEvict.class));
		}
		return new Cache("", "");
	}
	
	private static Cache createFromCacheableAnnotation(Cacheable cacheableAnnotation) {
		String cacheName = cacheableAnnotation.name();
		String cacheKey = cacheableAnnotation.key().substring(1);
		return new Cache(cacheName, cacheKey);
	}
	
	private static Cache createFromCachePutAnnotation(CachePut cachePutAnnotation) {
		String cacheName = cachePutAnnotation.name();
		String cacheKey = cachePutAnnotation.key().substring(1);
		return new Cache(cacheName, cacheKey);
	}
	
	private static Cache createFromCacheEvitAnnotation(CacheEvict cacheEvictAnnotation) {
		String cacheName = cacheEvictAnnotation.name();
		return new Cache(cacheName);
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

