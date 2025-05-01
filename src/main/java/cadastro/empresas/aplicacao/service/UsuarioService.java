package cadastro.empresas.aplicacao.service;

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
		if(!usuario.getPassword().equals(foundedUser.getPassword())) {
			throw new BadRequestException();
		}
	}
	
	public Usuario createUser(Usuario usuario) {
		return repository.create(usuario);
	}
	
}
