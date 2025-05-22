package cadastro.empresas.aplicacao.config.cache;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import javax.interceptor.InvocationContext;

import com.mysql.cj.util.StringUtils;

import cadastro.empresas.aplicacao.interceptor.InvocationParameters;

public class CacheHelper {

	public static String findCacheName(InvocationContext invocationContext) {
		List<InvocationParameters> parameters = InvocationParameters.getParameters(invocationContext);
		Method method = invocationContext.getMethod();
		Cache cache = Cache.createFromMethod(method);
		
		buildCache(cache, parameters);
		if(StringUtils.isNullOrEmpty(cache.getName())) {
			return null;
		}
		return cache.getName();
	}
	
	private static void buildCache(Cache cache, List<InvocationParameters> parameters) {
		for(InvocationParameters parameter: parameters) {
			if(parameter.getName().equals(cache.getKey())) {
				fillCacheName(cache, parameter.getValue());
			}
		}
	}
	
	private static void fillCacheName(Cache cache, Object parameter) {
		Field[] fields = parameter.getClass().getDeclaredFields();
		for(Field field: fields) {
			fillCacheName(cache, field, parameter);
		}
	}
	
	private static void fillCacheName(Cache cache, Field field, Object parameter) {
		try {
			field.setAccessible(true);
			cache.updateCacheName(field.getName(), field.get(parameter));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}finally {
			field.setAccessible(false);
		}

	}
	
}
