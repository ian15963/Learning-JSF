package cadastro.empresas.aplicacao.util;

import java.util.Arrays;

import org.primefaces.context.RequestContext;

public class ViewUtils {

	public static void updateComponents(String ...components) {
		RequestContext.getCurrentInstance().update(Arrays.asList(components));;
	}
	
}
