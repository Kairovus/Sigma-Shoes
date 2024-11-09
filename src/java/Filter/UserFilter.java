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
@WebFilter(servletNames = {"UserLogin", "UserRegister"})
public class UserFilter implements Filter {
    
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
        boolean isUser = session != null && session.getAttribute("username") != null && session.getAttribute("isUser").equals(true);
        if (!isUser) {
            if (request.getRequestURI().equals(request.getContextPath() + "/cart")) {
                response.sendRedirect(request.getContextPath() + "/login");
            }
            chain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }

    @Override
    public void destroy() {
    }
}