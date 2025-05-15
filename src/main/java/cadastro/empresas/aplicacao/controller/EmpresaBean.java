package cadastro.empresas.aplicacao.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.mysql.cj.util.StringUtils;

import cadastro.empresas.aplicacao.converter.RamoAtividadeConverter;
import cadastro.empresas.aplicacao.dto.EmpresaDto;
import cadastro.empresas.aplicacao.dto.RamoAtividadeDto;
import cadastro.empresas.aplicacao.mapper.EmpresaMapper;
import cadastro.empresas.aplicacao.model.Empresa;
import cadastro.empresas.aplicacao.model.enums.TipoEmpresa;
import cadastro.empresas.aplicacao.repository.EmpresaRepository;
import cadastro.empresas.aplicacao.repository.RamoAtividadeRepository;
import cadastro.empresas.aplicacao.service.EmpresaService;
import cadastro.empresas.aplicacao.util.CustomFacesMessage;
import cadastro.empresas.aplicacao.util.Page;
import cadastro.empresas.aplicacao.util.Transactional;

@Named
@ViewScoped
public class EmpresaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EmpresaRepository repository;
	@Inject
	private RamoAtividadeRepository ramoAtividadeRepository;
	@Inject
	private EmpresaService empresaService;
	private LazyDataModel<EmpresaDto> empresas;
	private EmpresaDto empresa;
	private String razaoSocial;
	private Converter<RamoAtividadeDto> converter;
	
	private LazyDataModel<EmpresaDto> createLazyDataModel(){
		return new LazyDataModel<EmpresaDto>() {
			private static final long serialVersionUID = 710412098474697979L;
			
			public List<EmpresaDto> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
				Page pageInfo = new Page(first, pageSize, sortField, sortOrder);
                List<EmpresaDto> data = empresaService.fetchEmpresas(pageInfo);
                int total = repository.totalElements();

                this.setRowCount(total);
                return data;
            }
        };
	}
	
	public void pesquisarEmpresa() {
//		empresas = createrepository.search(razaoSocial);
//		if(empresas.isEmpty()) {
//			CustomFacesMessage.info("Nenhuma empresa encontrada");
//		}
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
	
	public void listarEmpresas() {
		empresas = createLazyDataModel();
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

	public LazyDataModel<EmpresaDto> getEmpresas() {
		return empresas;
	}

	public Converter getConverter() {
		return converter;
	}

	public void setConverter(Converter converter) {
		this.converter = converter;
	}
	
	
	
}
