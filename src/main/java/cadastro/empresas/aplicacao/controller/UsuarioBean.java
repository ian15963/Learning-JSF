package cadastro.empresas.aplicacao.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

@SessionScoped
public class UsuarioBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String email;
	private Boolean isAuthenticated;
	
	public UsuarioBean() {
		
	}
	
	public UsuarioBean(String email, Boolean isAuthenticated) {
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
	
	public Boolean isAuthenticated() {
		return isAuthenticated;
	}
	
	public void setIsAuthenticated(Boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}

	
	
}
