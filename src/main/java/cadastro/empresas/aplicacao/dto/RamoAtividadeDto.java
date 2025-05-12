package cadastro.empresas.aplicacao.dto;

import cadastro.empresas.aplicacao.model.RamoAtividade;

public class RamoAtividadeDto {

	private Long id;
	private String descricao;
	
	public RamoAtividadeDto(Long id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}
	
	public RamoAtividadeDto(RamoAtividade ramoAtividade) {
		super();
		this.id = ramoAtividade.getId();
		this.descricao = ramoAtividade.getDescricao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
