package cadastro.empresas.aplicacao.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

@SessionScoped
public class UsuarioBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String email;
	private boolean isAuthenticated;
	
	public UsuarioBean() {
		
	}
	
	public UsuarioBean(String email, boolean isAuthenticated) {
		super();
		this.email = email;
		this.isAuthenticated = isAuthenticated;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isAuthenticated() {
		return isAuthenticated;
	}
	
	public void setIsAuthenticated(Boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}

	
	
}
