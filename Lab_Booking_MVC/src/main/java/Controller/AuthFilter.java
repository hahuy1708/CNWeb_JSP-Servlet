package Controller;

import Model.bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*", "/teacher/*"})
public class AuthFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        
        User user = (session != null) ? (User) session.getAttribute("user") : null;
        String uri = req.getRequestURI();

        if (user == null) {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        
        if (uri.contains("/admin/") && !"ADMIN".equals(user.getRole())) {
            res.sendRedirect(req.getContextPath() + "/unauthorized.jsp");
            return;
        } else if (uri.contains("/teacher/") && !"TEACHER".equals(user.getRole())) {
            res.sendRedirect(req.getContextPath() + "/unauthorized.jsp");
            return;
        }

        chain.doFilter(request, response);
    }
}
