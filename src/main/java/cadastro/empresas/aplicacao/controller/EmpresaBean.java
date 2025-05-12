package cadastro.empresas.aplicacao.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.mysql.cj.util.StringUtils;

import cadastro.empresas.aplicacao.converter.RamoAtividadeConverter;
import cadastro.empresas.aplicacao.dto.EmpresaDto;
import cadastro.empresas.aplicacao.dto.RamoAtividadeDto;
import cadastro.empresas.aplicacao.mapper.EmpresaMapper;
import cadastro.empresas.aplicacao.model.Empresa;
import cadastro.empresas.aplicacao.model.RamoAtividade;
import cadastro.empresas.aplicacao.model.enums.TipoEmpresa;
import cadastro.empresas.aplicacao.repository.EmpresaRepository;
import cadastro.empresas.aplicacao.repository.RamoAtividadeRepository;
import cadastro.empresas.aplicacao.util.CustomFacesMessage;
import cadastro.empresas.aplicacao.util.Transactional;

@Named
@ViewScoped
public class EmpresaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EmpresaRepository repository;
	@Inject
	private RamoAtividadeRepository ramoAtividadeRepository;
	private List<EmpresaDto> empresas;
	private EmpresaDto empresa;
	private String razaoSocial;
	private Converter<RamoAtividadeDto> converter;
	
	public void pesquisarEmpresa() {
		empresas = repository.search(razaoSocial);
		if(empresas.isEmpty()) {
			CustomFacesMessage.info("Nenhuma empresa encontrada");
		}
	}
	
	public void prepararEmpresa() {
		empresa = new EmpresaDto();
	}
	
	public void prepararEdicao() {
		Empresa entity = repository.findById(empresa.getId());
		empresa = EmpresaMapper.toDto(entity);
		converter = new RamoAtividadeConverter(Arrays.asList(empresa.getRamoAtividade()));
	}
	
	public List<RamoAtividadeDto> completarRamoAtividade(String termo){
		List<RamoAtividadeDto> ramos = ramoAtividadeRepository.search(termo);
		
		converter = new RamoAtividadeConverter(ramos);
		
		return ramos;
	}
	
	@Transactional
	public void listarEmpresas() {
		ramoAtividadeRepository.findAll();
		empresas = repository.findAll();
	}
	
	private void salvar() {
		Empresa entity = EmpresaMapper.toEntity(empresa);
		this.repository.create(entity);
		
		if(isRazaoSocialBlank()) {
			listarEmpresas();
		}else{
			pesquisarEmpresa();
		}
		
		CustomFacesMessage.info("Empresa adicionada com sucesso!");
		
		RequestContext.getCurrentInstance()
			.update(Arrays
					.asList("formulario:tabelaEmpresa", "formulario:globalMessage"));
	}
	
	private void update() {
		Empresa entity = EmpresaMapper.toEntity(empresa);
		this.repository.update(entity);
		
		if(isRazaoSocialBlank()) {
			listarEmpresas();
		}else{
			pesquisarEmpresa();
		}
		
		CustomFacesMessage.info("Empresa atualizada com sucesso!");
		
		RequestContext.getCurrentInstance()
			.update(Arrays
					.asList("formulario:tabelaEmpresa", "formulario:globalMessage"));
	}
	
	public void delete() {
		Empresa entity = EmpresaMapper.toEntity(empresa);
		this.repository.delete(entity);
		
		if(isRazaoSocialBlank()) {
			listarEmpresas();
		}else{
			pesquisarEmpresa();
		}
		
		CustomFacesMessage.info("Empresa deletada com sucesso!");
		
		RequestContext.getCurrentInstance()
			.update(Arrays
					.asList("formulario:tabelaEmpresa", "formulario:globalMessage"));
	}
	
	public void buttonAction() {
		if(Objects.nonNull(empresa) && Objects.nonNull(empresa.getId())) {
			this.update();
		}else {
			this.salvar();
		}
	}
	
	private boolean isRazaoSocialBlank() {
		return StringUtils.isNullOrEmpty(razaoSocial);
	}
	
	public TipoEmpresa[] getTiposEmpresa() {
		return TipoEmpresa.values();
	}

	public boolean isEmpresaSelected() {
		return Objects.nonNull(empresa) && Objects.nonNull(empresa.getId());
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public EmpresaDto getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaDto empresa) {
		this.empresa = empresa;
	}
	
	public List<EmpresaDto> getEmpresas(){
		return empresas;
	}

	public Converter getConverter() {
		return converter;
	}

	public void setConverter(Converter converter) {
		this.converter = converter;
	}
	
	
	
}
