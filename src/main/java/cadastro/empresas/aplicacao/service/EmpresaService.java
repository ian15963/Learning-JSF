package cadastro.empresas.aplicacao.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cadastro.empresas.aplicacao.dto.EmpresaDto;
import cadastro.empresas.aplicacao.interceptor.cache.annotations.CacheEvict;
import cadastro.empresas.aplicacao.interceptor.cache.annotations.Cacheable;
import cadastro.empresas.aplicacao.interceptor.transactional.Transactional;
import cadastro.empresas.aplicacao.model.Empresa;
import cadastro.empresas.aplicacao.repository.EmpresaRepository;
import cadastro.empresas.aplicacao.util.Page;

@Named
@ApplicationScoped
@Transactional
public class EmpresaService {

	@Inject
	private EmpresaRepository repository;
	@Inject
	private RamoAtividadeService ramoAtividadeService;
	
	@Cacheable(name = "empresas", key = "#pageInfo")
	public List<EmpresaDto> fetchEmpresas(Page pageInfo){
		ramoAtividadeService.findAll();
		List<EmpresaDto> empresas = repository.findAll(pageInfo);
		return empresas;
	}
	
	@Cacheable(name = "empresa", key = "#id")
	public Empresa findById(Long id) {
		return repository.findById(id);
	}
	
	public List<EmpresaDto> searchEmpresas(Page pageInfo, String razaoSocial){
		ramoAtividadeService.findAll();
		return repository.search(pageInfo, razaoSocial);
	}
	
	@CacheEvict(name = "empresas", allEntries = true)
	public Empresa save(Empresa empresa) {
		return repository.create(empresa);
	}
	
	public Empresa update(Empresa empresa) {
		return repository.update(empresa);
	}
	
	public void delete(Empresa empresa) {
		repository.delete(empresa);
	}
	
	public int totalElements() {
		return repository.totalElements();
	}
	
	public int totalElements(String razaoSocial) {
		return repository.totalElementsFromSearch(razaoSocial);
	}
}
