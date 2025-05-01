package cadastro.empresas.aplicacao.util;

import java.io.IOException;

import javax.faces.context.FacesContext;

public class RedirectUtils {
	
	public static void redirectToPage(String page) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(page);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
