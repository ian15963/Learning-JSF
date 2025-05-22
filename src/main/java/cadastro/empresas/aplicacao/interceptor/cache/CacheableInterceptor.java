package cadastro.empresas.aplicacao.interceptor.cache;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
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
		Object[] parameters = invocationContext.getParameters();
		Parameter[] methodParameters = method.getParameters();
		Cacheable cacheableAnnotation =  method.getDeclaredAnnotation(Cacheable.class);
		String cacheName = cacheableAnnotation.name();
		String cacheKey = cacheableAnnotation.key().substring(1);
		for(int i = 0; i < parameters.length; i++) {
			String parameterName = methodParameters[i].getName();
			if(parameterName.equals(cacheKey)) {
				Field[] fields = parameters[i].getClass().getDeclaredFields();
				for(Field field: fields) {
					try {
						field.setAccessible(true);
						cacheName += String.format("#%s:%s",field.getName(), field.get(parameters[i]));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}finally {
						field.setAccessible(false);
					}
				}
			}
		}
		return cacheName;
	}
	
}
