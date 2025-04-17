package cadastro.empresas.aplicacao.util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class CustomFacesMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static void add(String msg, FacesMessage.Severity severity) {
		FacesMessage message = new FacesMessage(msg);
		message.setSeverity(severity);
		
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public static void info(String msg) {
		add(msg, FacesMessage.SEVERITY_INFO);
	}
	
}
