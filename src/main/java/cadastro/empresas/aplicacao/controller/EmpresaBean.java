package cadastro.empresas.aplicacao.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import com.mysql.cj.util.StringUtils;

import cadastro.empresas.aplicacao.converter.RamoAtividadeConverter;
import cadastro.empresas.aplicacao.dto.EmpresaDto;
import cadastro.empresas.aplicacao.dto.RamoAtividadeDto;
import cadastro.empresas.aplicacao.mapper.EmpresaMapper;
import cadastro.empresas.aplicacao.model.Empresa;
import cadastro.empresas.aplicacao.model.enums.TipoEmpresa;
import cadastro.empresas.aplicacao.repository.RamoAtividadeRepository;
import cadastro.empresas.aplicacao.service.EmpresaService;
import cadastro.empresas.aplicacao.util.CustomFacesMessage;
import cadastro.empresas.aplicacao.util.LazyDataModelUtils;
import cadastro.empresas.aplicacao.util.ViewUtils;

@Named
@ViewScoped
public class EmpresaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private RamoAtividadeRepository ramoAtividadeRepository;
	@Inject
	private EmpresaService service;
	private LazyDataModel<EmpresaDto> empresas;
	private EmpresaDto empresa;
	private String razaoSocial;
	private Converter<RamoAtividadeDto> converter;
	
	public void pesquisarEmpresa() {
		int totalElements = service.totalElements(razaoSocial);
		empresas = LazyDataModelUtils.createLazyDataModel(page -> {
			return service.searchEmpresas(page, razaoSocial);			
		}, totalElements); 
	}
	
	public void prepararEmpresa() {
		empresa = new EmpresaDto();
	}
	
	public void prepararEdicao() {
		Empresa entity = service.findById(empresa.getId());
		empresa = EmpresaMapper.toDto(entity);
		converter = new RamoAtividadeConverter(Arrays.asList(empresa.getRamoAtividade()));
	}
	
	public List<RamoAtividadeDto> completarRamoAtividade(String termo){
		List<RamoAtividadeDto> ramos = ramoAtividadeRepository.search(termo);
		converter = new RamoAtividadeConverter(ramos);
		return ramos;
	}
	
	public void listarEmpresas() {
		int totalElements = service.totalElements();
		empresas = LazyDataModelUtils.createLazyDataModel(page -> {
			return service.fetchEmpresas(page);
		}, totalElements);
	}
	
	private void salvar() {
		Empresa entity = EmpresaMapper.toEntity(empresa);
		this.service.save(entity);
		
		renderTableContent();
		CustomFacesMessage.info("Empresa adicionada com sucesso!");
		ViewUtils.updateComponents("formulario:tabelaEmpresa", "formulario:globalMessage");
	}
	
	private void update() {
		Empresa entity = EmpresaMapper.toEntity(empresa);
		this.service.update(entity);
		
		renderTableContent();
		CustomFacesMessage.info("Empresa atualizada com sucesso!");
		ViewUtils.updateComponents("formulario:tabelaEmpresa", "formulario:globalMessage");
	}
	
	public void delete() {
		Empresa entity = EmpresaMapper.toEntity(empresa);
		this.service.delete(entity);
		
		renderTableContent();
		CustomFacesMessage.info("Empresa deletada com sucesso!");
		ViewUtils.updateComponents("formulario:tabelaEmpresa", "formulario:globalMessage");
	}
	
	private void renderTableContent() {
		if(isRazaoSocialBlank()) {
			listarEmpresas();
		}else{
			pesquisarEmpresa();
		}
	}
	
	public void buttonAction() {
		if(Objects.nonNull(empresa) && Objects.nonNull(empresa.getId())) {
			this.update();
		}else {
			this.salvar();
		}
	}
	
	private boolean isRazaoSocialBlank() {
		return StringUtils.isNullOrEmpty(razaoSocial.trim());
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

	public LazyDataModel<EmpresaDto> getEmpresas() {
		return empresas;
	}

	public Converter<RamoAtividadeDto> getConverter() {
		return converter;
	}

	public void setConverter(Converter<RamoAtividadeDto> converter) {
		this.converter = converter;
	}
	
	
	
}
