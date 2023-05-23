/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nipun
 */
public class AuthenticationFilter implements Filter{

    private ServletContext context;
    @Override
    public void init(FilterConfig fc) throws ServletException {
     this.context = fc.getServletContext();
     this.context.log("RequestLogging Filter Initialized");
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest req =(HttpServletRequest)sr;
        HttpServletResponse res =(HttpServletResponse)sr1;
        
        String uri = req.getRequestURI();
        this.context.log("Request Reqource:: "+uri);
        
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        
        if(username ==null){
            this.context.log("Unauthenticated access request");
            res.sendRedirect("../login.jsp");
        }else{
            fc.doFilter(sr, sr1);
        }
    }

    @Override
    public void destroy() {
       context = null;
    }
    
}
