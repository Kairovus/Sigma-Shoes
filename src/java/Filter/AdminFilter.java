package Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author silviarianto
 */
@WebFilter(urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                    ServletResponse servletResponse, FilterChain chain)
                    throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/admin";
        boolean isLoginRequest = request.getRequestURI().equals(loginURI);
        boolean isAdmin = session != null && session.getAttribute("adminName") != null && session.getAttribute("isAdmin").equals(true);
        if (isLoginRequest && isAdmin) {
            response.sendRedirect(loginURI + "/dashboard");
        } else if (isLoginRequest || isAdmin) {
            chain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {
    }
}