package cadastro.empresas.aplicacao.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cadastro.empresas.aplicacao.dto.AuthenticateUserDto;
import cadastro.empresas.aplicacao.mapper.UsuarioMapper;
import cadastro.empresas.aplicacao.model.Usuario;
import cadastro.empresas.aplicacao.service.UsuarioService;
import cadastro.empresas.aplicacao.util.RedirectUtils;

@Named
@SessionScoped
public class UsuarioAutenticadoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioService service;
	private AuthenticateUserDto usuarioDto = new AuthenticateUserDto();
	
	public void authenticate() {
		Usuario usuario = UsuarioMapper.toEntity(usuarioDto);
		service.authenticate(usuario);
		RedirectUtils.redirectToPage("GestaoEmpresas.xhtml");
	}

	public AuthenticateUserDto getUsuarioDto() {
		return usuarioDto;
	}

	public void setUsuarioDto(AuthenticateUserDto usuarioDto) {
		this.usuarioDto = usuarioDto;
	}
	
}
