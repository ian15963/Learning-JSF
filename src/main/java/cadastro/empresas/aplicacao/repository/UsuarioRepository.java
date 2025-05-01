package cadastro.empresas.aplicacao.repository;

import cadastro.empresas.aplicacao.model.Usuario;

public interface UsuarioRepository {
	
	Usuario create(Usuario usuario);
	Usuario findByUsername(String username);
	
}
