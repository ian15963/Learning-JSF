package cadastro.empresas.aplicacao.interceptor.cache;

import java.lang.reflect.Method;
import java.util.Objects;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import cadastro.empresas.aplicacao.config.CacheProvider;
import cadastro.empresas.aplicacao.interceptor.cache.annotations.CachePut;

@Interceptor
@CachePut
@Priority(Interceptor.Priority.APPLICATION)
public class CachePutInterceptor {

	@Inject
	private CacheProvider cacheProvider;
	
	@AroundInvoke
	public Object invoke(InvocationContext invocationContext) throws Exception {
		
		String cacheName = findCacheName(invocationContext);
		if(Objects.isNull(cacheName)) {
			//TODO: lançar exceção.
			return invocationContext.proceed();
		}
		
		Object invocationResult = invocationContext.proceed();
		if(Objects.isNull(invocationResult)) {
			//TODO: lançar exceção.
			return invocationResult;
		}
		cacheProvider.put(cacheName, invocationResult);
		return invocationResult;
	}
	
	private String findCacheName(InvocationContext invocationContext) {
		Method method = invocationContext.getMethod();
		CachePut cacheableAnnotation =  method.getDeclaredAnnotation(CachePut.class);
		return cacheableAnnotation.name();
	}
	
}
