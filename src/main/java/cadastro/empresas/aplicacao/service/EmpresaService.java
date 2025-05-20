package cadastro.empresas.aplicacao.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cadastro.empresas.aplicacao.dto.EmpresaDto;
import cadastro.empresas.aplicacao.interceptor.Transactional;
import cadastro.empresas.aplicacao.interceptor.cache.annotations.Cacheable;
import cadastro.empresas.aplicacao.repository.EmpresaRepository;
import cadastro.empresas.aplicacao.repository.RamoAtividadeRepository;
import cadastro.empresas.aplicacao.util.Page;

@Named
@ApplicationScoped
public class EmpresaService {

	@Inject
	private EmpresaRepository repository;
	@Inject
	private RamoAtividadeRepository ramoAtividadeRepository;
	
	@Cacheable(name = "empresas")
	@Transactional
	public List<EmpresaDto> fetchEmpresas(Page pageInfo){
		ramoAtividadeRepository.findAll();
		List<EmpresaDto> empresas = repository.findAll(pageInfo);
		return empresas;
	}
	
	@Transactional
	public List<EmpresaDto> searchEmpresas(Page pageInfo, String razaoSocial){
		ramoAtividadeRepository.findAll();
		return repository.search(pageInfo, razaoSocial);
	}
}
