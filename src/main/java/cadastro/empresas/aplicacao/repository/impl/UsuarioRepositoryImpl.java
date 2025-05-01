package cadastro.empresas.aplicacao.repository.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import cadastro.empresas.aplicacao.model.Usuario;
import cadastro.empresas.aplicacao.repository.UsuarioRepository;

@ApplicationScoped
public class UsuarioRepositoryImpl implements UsuarioRepository{

	@Inject
	private EntityManager entityManager;
	
	@Override
	public Usuario create(Usuario usuario) {
		return entityManager.merge(usuario);
	}

	@Override
	public Usuario findByUsername(String username) {
		TypedQuery<Usuario> query = entityManager.createQuery("FROM Usuario WHERE username = :username", Usuario.class);
		query.setParameter("username", username);
		return query.getSingleResult();
	}

}
