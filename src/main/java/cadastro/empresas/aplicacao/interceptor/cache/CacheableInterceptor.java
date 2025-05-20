package cadastro.empresas.aplicacao.interceptor.cache;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Objects;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import cadastro.empresas.aplicacao.config.CacheProvider;
import cadastro.empresas.aplicacao.interceptor.cache.annotations.Cacheable;

@Interceptor
@Cacheable
@Priority(Interceptor.Priority.APPLICATION)
public class CacheableInterceptor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CacheProvider cacheProvider;

	@AroundInvoke
	public Object invoke(InvocationContext invocationContext) throws Exception {
		
		String cacheName = findCacheName(invocationContext);
		if(Objects.isNull(cacheName)) {
			return invocationContext.proceed();
		}
		
		if(cacheProvider.contains(cacheName)) {
			return cacheProvider.get(cacheName);
		}
		
		Object invocationResult = invocationContext.proceed();
		if(Objects.isNull(invocationResult)) {
			return invocationResult;
		}
		cacheProvider.put(cacheName, invocationResult);
		return invocationResult;
	}
	
	private String findCacheName(InvocationContext invocationContext) {
		Method method = invocationContext.getMethod();
		Cacheable cacheableAnnotation =  method.getDeclaredAnnotation(Cacheable.class);
		return cacheableAnnotation.name();
	}
	
}
