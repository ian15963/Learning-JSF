package cadastro.empresas.aplicacao.mapper;

import java.util.Objects;

import cadastro.empresas.aplicacao.dto.AuthenticateUserDto;
import cadastro.empresas.aplicacao.dto.UsuarioDto;
import cadastro.empresas.aplicacao.model.Usuario;

public class UsuarioMapper {
	
	public static Usuario toEntity(UsuarioDto dto) {
		if(Objects.isNull(dto)) {
			return null;
		}
		
		Usuario usuario = new Usuario();
		usuario.setId(dto.getId());
		usuario.setName(dto.getName());
		usuario.setPassword(dto.getPassword());
		usuario.setUsername(dto.getUsername());
		return usuario;
	}
	
	public static Usuario toEntity(AuthenticateUserDto dto) {
		if(Objects.isNull(dto)) {
			return null;
		}
		
		Usuario usuario = new Usuario();
		usuario.setUsername(dto.getUsername());
		usuario.setPassword(dto.getPassword());
		return usuario;
	}

}
