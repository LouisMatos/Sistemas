package org.fatec.filter;
import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fatec.model.Usuario;
public class LoginFilter implements Filter {
	
	ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
	private final static String FILTER_APPLIED = "_security_filter_applied";
	public LoginFilter() {
	}
	public void init(FilterConfig arg0) throws ServletException {
	}
	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hreq = (HttpServletRequest) request;
		HttpServletResponse hresp = (HttpServletResponse) response;
		HttpSession session = hreq.getSession();
		hreq.getPathInfo();
		String paginaAtual = new String(hreq.getRequestURL());
		
		// dont filter login.jsp because otherwise an endless loop.
		// & only filter .jsp otherwise it will filter all images etc as well.
		if ((request.getAttribute(FILTER_APPLIED) == null) && paginaAtual != null
				&& (!paginaAtual.endsWith("index.xhtml"))
				&& (paginaAtual.endsWith(".xhtml"))) {
			request.setAttribute(FILTER_APPLIED, Boolean.TRUE);
			// If the session bean is not null get the session bean property
			// username.
			Usuario user = null;
			if ((session.getAttribute("info_user")) != null) {
				 user = (Usuario) session.getAttribute("info_user");
			}
			if ((user == null) || (user.equals(""))) {
				//hreq.getRealPath("/index.xhtml");
				
				hresp.sendRedirect(context.getRequestContextPath() + "../../../../index.xhtml");
				return;
			}
		}
		// deliver request to next filter
		chain.doFilter(request, response);
	}
}