package cadastro.empresas.aplicacao.repository.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import cadastro.empresas.aplicacao.dto.EmpresaDto;
import cadastro.empresas.aplicacao.interceptor.Transactional;
import cadastro.empresas.aplicacao.model.Empresa;
import cadastro.empresas.aplicacao.repository.EmpresaRepository;
import cadastro.empresas.aplicacao.util.Page;

@Transactional
@ApplicationScoped
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
	public Empresa update(Empresa empresa) {
		return entityManager.merge(empresa);
	}

	@Override
	public void delete(Empresa empresa) {
		empresa = findById(empresa.getId());
		entityManager.remove(empresa);
	}

	@Override
	public List<EmpresaDto> search(Page page, String razaoSocial) {
		String jpql = "SELECT "
				+ "new cadastro.empresas.aplicacao.dto.EmpresaDto("
				+ "e.id,"
				+ "e.razaoSocial,"
				+ "e.nomeFantasia,"
				+ "e.tipo,"
				+ "e.ramoAtividade)"
				+ " FROM Empresa e WHERE e.razaoSocial LIKE CONCAT('%', :razaoSocial, '%')";
		
		jpql = page.buildSortQuery(jpql);
		TypedQuery<EmpresaDto> query = entityManager.createQuery(jpql, EmpresaDto.class);
		
		query.setParameter("razaoSocial", razaoSocial);
		return query.setFirstResult(page.getPage())
				.setMaxResults(page.getPageSize())
				.getResultList();
	}
	
	@Override
	public int totalElementsFromSearch(String razaoSocial) {
		Query query = entityManager.createQuery("SELECT COUNT(e) FROM Empresa e WHERE e.razaoSocial LIKE CONCAT('%', :razaoSocial, '%')");
		query.setParameter("razaoSocial", razaoSocial);
		return ((Long) query.getSingleResult()).intValue();
	}

	@Override
	public List<EmpresaDto> findAll(Page pageInfo) {
		String jpql = "SELECT new cadastro.empresas.aplicacao.dto.EmpresaDto("
				+ "e.id,"
				+ "e.razaoSocial,"
				+ "e.nomeFantasia,"
				+ "e.tipo,"
				+ "e.ramoAtividade) "
				+ "FROM Empresa e";
		
		jpql = pageInfo.buildSortQuery(jpql);
		
		TypedQuery<EmpresaDto> query = entityManager.createQuery(jpql, EmpresaDto.class);
		
		return query.setFirstResult(pageInfo.getPage())
				.setMaxResults(pageInfo.getPageSize())
				.getResultList();
	}

	@Override
	public int totalElements() {
		Query query = entityManager.createQuery("SELECT COUNT(e) FROM Empresa e");
		return ((Long) query.getSingleResult()).intValue();
	}

}
