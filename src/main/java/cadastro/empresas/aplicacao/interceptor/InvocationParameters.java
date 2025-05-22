package cadastro.empresas.aplicacao.interceptor;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import javax.interceptor.InvocationContext;

public class InvocationParameters {
	
	private String name;
	private Object value;

	private InvocationParameters() {
	}
	
	private InvocationParameters(String name, Object value) {
		this.name = name;
		this.value = value;
	}
	
	public static List<InvocationParameters> getParameters(InvocationContext invocationContext){
		Method method = invocationContext.getMethod();
		Object[] parametersValues = invocationContext.getParameters();
		Parameter[] parametersNames = method.getParameters();
		
		List<InvocationParameters> invocationParameters = new ArrayList<InvocationParameters>();
		for(int i = 0; i < parametersValues.length; i++) {
			InvocationParameters invocationParameter = new InvocationParameters(parametersNames[i].getName(), parametersValues[i]);
			invocationParameters.add(invocationParameter);
		}
		return invocationParameters;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}
	
}
