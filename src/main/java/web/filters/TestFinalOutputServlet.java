/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.filters;

import domain.model.Wallet;
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

public class TestFinalOutputServlet implements Filter{

   public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest)request;
                HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session = req.getSession();

		Wallet name = (Wallet) session.getAttribute("wallet");
		if(name!=null){
                    return;
		}
		else{
			resp.sendRedirect("addWallet.html");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}
    
    
}
