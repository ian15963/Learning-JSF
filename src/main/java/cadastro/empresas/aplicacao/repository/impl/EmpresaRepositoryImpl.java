package cadastro.empresas.aplicacao.repository.impl;

import java.util.List;

import cadastro.empresas.aplicacao.model.Empresa;
import cadastro.empresas.aplicacao.repository.EmpresaRepository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class EmpresaRepositoryImpl implements EmpresaRepository{
	
	@Inject
	private EntityManager entityManager;

	@Override
	public Empresa findById(Long id) {
		return entityManager.find(Empresa.class, id);
	}

	@Override
	public Empresa create(Empresa empresa) {
		return entityManager.merge(empresa);
	}

	@Override
	public Empresa update(Empresa empresa, Long id) {
		empresa.setId(id);
		return entityManager.merge(empresa);
	}

	@Override
	public void delete(Empresa empresa) {
		entityManager.remove(empresa);;
	}

	@Override
	public List<Empresa> search(String razaoSocial) {
		TypedQuery<Empresa> query = entityManager.createQuery("FROM Empresa e WHERE e.razaoSocial LIKE CONCAT('%', :razaoSocial, '%')", Empresa.class);
		query.setParameter("nomeEmpresa", razaoSocial);
		return query.getResultList();
	}

}
