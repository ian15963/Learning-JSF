package cadastro.empresas.aplicacao.interceptor.cache;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.mysql.cj.util.StringUtils;

import cadastro.empresas.aplicacao.config.cache.CacheProvider;
import cadastro.empresas.aplicacao.config.cache.Cache;
import cadastro.empresas.aplicacao.config.cache.CacheHelper;
import cadastro.empresas.aplicacao.interceptor.InvocationParameters;
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
		
		String cacheName = CacheHelper.findCacheName(invocationContext);
		if(Objects.isNull(cacheName)) {
			return invocationContext.proceed();
		}
		
		if(cacheProvider.contains(cacheName)) {
			return cacheProvider.get(cacheName);
		}
		
		Object invocationResult = invocationContext.proceed();
		updateCache(cacheName, invocationResult);
		return invocationResult;
	}
	
	private void updateCache(String cacheName, Object invocationResult) {
		if(Objects.isNull(invocationResult)) {
			return;
		}
		cacheProvider.put(cacheName, invocationResult);
	}
	
	
	
}
