package cadastro.empresas.aplicacao.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import cadastro.empresas.aplicacao.dto.RamoAtividadeDto;

@Entity
@Table(name = "ramo_atividade")
public class RamoAtividade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String descricao;
	
	public RamoAtividade() {	
	}
	
	public RamoAtividade(RamoAtividadeDto dto) {
		this.id = dto.getId();
		this.descricao = dto.getDescricao();
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
