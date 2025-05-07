package cadastro.empresas.aplicacao.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

import cadastro.empresas.aplicacao.model.Usuario;
import cadastro.empresas.aplicacao.repository.UsuarioRepository;

@ApplicationScoped
public class UsuarioService {

	@Inject
	private UsuarioRepository repository;
	
	public void authenticate(Usuario usuario) {
		Usuario foundedUser = repository.findByUsername(usuario.getUsername());
		String password = passwordEncryption(usuario.getPassword());
		if(!password.equals(foundedUser.getPassword())) {
			throw new BadRequestException();
		}
	}
	
	public Usuario createUser(Usuario usuario) {
		usuario.setPassword(passwordEncryption(usuario.getPassword()));
		return repository.create(usuario);
	}
	
	private String passwordEncryption(String password) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
			byte[] passwordBytes = password.getBytes();
			passwordBytes = messageDigest.digest(passwordBytes);
			password =  Base64.getEncoder().encodeToString(passwordBytes);			
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return password;
	}
	
}
