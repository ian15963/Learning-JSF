package cadastro.empresas.aplicacao.interceptor.cache;

import java.util.Objects;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import cadastro.empresas.aplicacao.config.cache.CacheHelper;
import cadastro.empresas.aplicacao.config.cache.CacheProvider;
import cadastro.empresas.aplicacao.interceptor.cache.annotations.CacheEvict;

@Interceptor
@CacheEvict
@Priority(Interceptor.Priority.APPLICATION)
public class CacheEvictInterceptor {
	
	@Inject
	private CacheProvider cacheProvider;

	@AroundInvoke
	public Object invoke(InvocationContext invocationContext) throws Exception {
		if(isRemoveAllEntriesEnabled(invocationContext)) {
			removeAllEntries(invocationContext);
			return invocationContext.proceed();
		}
		String cacheName = CacheHelper.findCacheName(invocationContext);
		validateCacheName(cacheName);
		cacheProvider.remove(cacheName);
		return invocationContext.proceed();
	}
	
	private void removeAllEntries(InvocationContext invocationContext) {
		CacheEvict annotation = invocationContext.getMethod().getDeclaredAnnotation(CacheEvict.class);
		String cacheName = annotation.name();
		cacheProvider.removeAll(cacheName);
	}
	
	private boolean isRemoveAllEntriesEnabled(InvocationContext invocationContext) {
		CacheEvict annotation = invocationContext.getMethod().getDeclaredAnnotation(CacheEvict.class);
		return annotation.allEntries();
	}
	
	private void validateCacheName(String cacheName) {
		if(Objects.isNull(cacheName)) {
			throw new IllegalArgumentException("Não existe cache com essa chave");
		}
	}
	
}
