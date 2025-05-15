package cadastro.empresas.aplicacao.repository.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.primefaces.model.SortOrder;

import cadastro.empresas.aplicacao.dto.EmpresaDto;
import cadastro.empresas.aplicacao.model.Empresa;
import cadastro.empresas.aplicacao.repository.EmpresaRepository;
import cadastro.empresas.aplicacao.util.Page;
import cadastro.empresas.aplicacao.util.Transactional;

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
	public List<EmpresaDto> search(String razaoSocial) {
		TypedQuery<EmpresaDto> query = entityManager.createQuery("SELECT "
				+ "new cadastro.empresas.aplicacao.dto.EmpresaDto("
				+ "e.id,"
				+ "e.razaoSocial,"
				+ "e.nomeFantasia,"
				+ "e.tipoEmpresa,"
				+ "new cadastro.empresas.aplicacao.dto.RamoAtividadeDto(e.ramoAtividade))"
				+ " FROM Empresa e WHERE e.razaoSocial LIKE CONCAT('%', :razaoSocial, '%')", EmpresaDto.class);
		query.setParameter("razaoSocial", razaoSocial);
		return query.getResultList();
	}

	@Override
	public List<EmpresaDto> findAll(Page pageInfo) {
		boolean ascending = SortOrder.ASCENDING.equals(pageInfo.getOrderBy());
		String jpql = "SELECT new cadastro.empresas.aplicacao.dto.EmpresaDto("
				+ "e.id,"
				+ "e.razaoSocial,"
				+ "e.nomeFantasia,"
				+ "e.tipo,"
				+ "e.ramoAtividade) "
				+ "FROM Empresa e";
		if (pageInfo.getSortBy() != null) {
			jpql += " ORDER BY e." + pageInfo.getSortBy() + (ascending ? " ASC" : " DESC");
		}
		TypedQuery<EmpresaDto> query = entityManager
				.createQuery(jpql, 
						EmpresaDto.class);
		
		return query
				.setFirstResult(pageInfo.getPage())
				.setMaxResults(pageInfo.getPageSize())
				.getResultList();
	}

	@Override
	public int totalElements() {
		Query query = entityManager.createQuery("SELECT COUNT(e) FROM Empresa e");
		return ((Long) query.getSingleResult()).intValue();
	}

}
