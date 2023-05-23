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
public class LoginPageFilter implements Filter{

  

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest req =(HttpServletRequest)sr;
        HttpServletResponse res =(HttpServletResponse)sr1;
        
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userid");
        
        if(userId != null){
            
                res.sendRedirect("dashboard.jsp");
            
            
        }else{
            fc.doFilter(sr, sr1);
        }
    }

    @Override
    public void destroy() {
     
    }
    
}
