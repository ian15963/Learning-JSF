package cadastro.empresas.aplicacao.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cadastro.empresas.aplicacao.controller.UsuarioBean;

@WebFilter(urlPatterns = "*.xhtml")
public class SecurityFilter implements Filter{

	@Inject
	private UsuarioBean usuario;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;

		
		if(usuario.isAuthenticated() && !isUnauthenticatedURI(servletRequest)) {
			chain.doFilter(request, response);
			return;
		}
		
		if(isUnauthenticatedURI(servletRequest)) {
			chain.doFilter(request, response);
			return;
		}
		
		servletResponse.sendRedirect("%s/Login.xhtml"
				.formatted(servletRequest.getContextPath()));
		
	}
	
	private boolean isUnauthenticatedURI(HttpServletRequest request) {
		return request.getRequestURI().endsWith("Login.xhtml") || 
				request.getRequestURI().endsWith("Cadastro.xhtml") ||
				request.getRequestURI().contains("javax.faces.resource/");
	}

}
