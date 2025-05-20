package cadastro.empresas.aplicacao.interceptor.cache;

import java.lang.reflect.Method;
import java.util.Objects;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import cadastro.empresas.aplicacao.config.CacheProvider;
import cadastro.empresas.aplicacao.interceptor.cache.annotations.CacheEvict;
import cadastro.empresas.aplicacao.interceptor.cache.annotations.CachePut;

@Interceptor
@CacheEvict
@Priority(Interceptor.Priority.APPLICATION)
public class CacheEvictInterceptor {
	
	@Inject
	private CacheProvider cacheProvider;

	@AroundInvoke
	public Object invoke(InvocationContext invocationContext) {
		String cacheName = findCacheName(invocationContext);
		cacheProvider.remove(cacheName);
		return invocationContext;
	}
	
	private String findCacheName(InvocationContext invocationContext) {
		Method method = invocationContext.getMethod();
		CachePut cacheableAnnotation =  method.getDeclaredAnnotation(CachePut.class);
		
		String cacheName = cacheableAnnotation.name();
		validateCacheName(cacheName);
		return cacheName;
	}

	private void validateCacheName(String cacheName) {
		if(Objects.isNull(cacheName)) {
			throw new IllegalArgumentException("Não existe cache com essa chave");
		}
	}
	
}
