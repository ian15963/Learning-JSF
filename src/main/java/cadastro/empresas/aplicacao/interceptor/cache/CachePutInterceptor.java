package cadastro.empresas.aplicacao.interceptor.cache;

import java.util.Objects;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import cadastro.empresas.aplicacao.config.cache.CacheHelper;
import cadastro.empresas.aplicacao.config.cache.CacheProvider;
import cadastro.empresas.aplicacao.interceptor.cache.annotations.CachePut;

@Interceptor
@CachePut
@Priority(Interceptor.Priority.APPLICATION)
public class CachePutInterceptor {

	@Inject
	private CacheProvider cacheProvider;
	
	@AroundInvoke
	public Object invoke(InvocationContext invocationContext) throws Exception {
		String cacheName = CacheHelper.findCacheName(invocationContext);
		Object invocationResult = invocationResult(invocationContext);
		cacheProvider.put(cacheName, invocationResult);
		return invocationResult;
	}
	
	private Object invocationResult(InvocationContext invocationContext) throws Exception {
		Object invocationResult = invocationContext.proceed();
		validateInvocationResult(invocationResult);
		return invocationResult;
	}
	
	private void validateInvocationResult(Object invocationResult) {
		if(Objects.isNull(invocationResult)) {
			throw new IllegalArgumentException("Resultado da execução do método não pode ser nulo");
		}
	}
}
