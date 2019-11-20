package br.unaerp.integrador.auth;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author GabrielTeles
 * Classe resposnavel por filtar todas requisicoes da aplicacao
 */
public class AuthFilter implements Filter {
	public static String contexturl;

	/**
	 * Default constructor.
	 */
	public AuthFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (AuthFilter.contexturl == null) {
			AuthFilter.contexturl = ((HttpServletRequest) request).getContextPath();
		}

		String loginURL = AuthFilter.contexturl + "/auth.xhtml";

		HttpSession sess = ((HttpServletRequest) request).getSession(true);
		

		if (sess.getAttribute("user") == null) {
			((HttpServletResponse) response).sendRedirect(loginURL);

			sess.setAttribute("context", AuthFilter.contexturl);

		} else {
			chain.doFilter(request, response);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
