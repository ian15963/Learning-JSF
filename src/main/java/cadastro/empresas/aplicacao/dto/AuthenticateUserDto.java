package cadastro.empresas.aplicacao.dto;

import org.hibernate.validator.constraints.NotBlank;

public class AuthenticateUserDto {

	@NotBlank
	private String username;
	@NotBlank
	private String password;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
