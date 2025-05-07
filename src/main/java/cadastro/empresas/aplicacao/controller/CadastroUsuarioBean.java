package cadastro.empresas.aplicacao.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cadastro.empresas.aplicacao.dto.UsuarioDto;
import cadastro.empresas.aplicacao.mapper.UsuarioMapper;
import cadastro.empresas.aplicacao.model.Usuario;
import cadastro.empresas.aplicacao.service.UsuarioService;
import cadastro.empresas.aplicacao.util.RedirectUtils;

@Named
@RequestScoped
public class CadastroUsuarioBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioService service;
	private UsuarioDto usuarioDto = new UsuarioDto();
	
	public void createUser() {
		Usuario usuario = UsuarioMapper.toEntity(usuarioDto);
		service.createUser(usuario);
		RedirectUtils.redirectToPage("Login.xhtml");
	}

	public UsuarioDto getUsuarioDto() {
		return usuarioDto;
	}

	public void setUsuarioDto(UsuarioDto usuarioDto) {
		this.usuarioDto = usuarioDto;
	}
	
	
}
