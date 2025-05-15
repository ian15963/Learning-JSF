package cadastro.empresas.aplicacao.service;

import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.SortOrder;

import cadastro.empresas.aplicacao.dto.EmpresaDto;
import cadastro.empresas.aplicacao.repository.EmpresaRepository;
import cadastro.empresas.aplicacao.repository.RamoAtividadeRepository;
import cadastro.empresas.aplicacao.util.Transactional;

@Named
@ApplicationScoped
public class EmpresaService {

	@Inject
	private EmpresaRepository repository;
	@Inject
	private RamoAtividadeRepository ramoAtividadeRepository;
	
	@Transactional
	public List<EmpresaDto> fetchEmpresas(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters){
		ramoAtividadeRepository.findAll();
		return repository.findAll(first, pageSize, sortField, sortOrder);
	}
}
