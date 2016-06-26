package org.javachina.testsession.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ActionServlet
 */
//@WebServlet("/ActionServlet")
public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doRequest(request, response);
	}
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uid = request.getParameter("uid");
		String pwd = request.getParameter("pwd");
		
		String uidFromDb = "scott";
		String pwdFromDb = "tiger";
		//System.out.println(uid+":"+pwd);
		
		String act = request.getParameter("act");
		if("invalid".equals(act)){
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}else{
		if(uidFromDb.equals(uid) && pwdFromDb.equals(pwd)){
			HttpSession session = request.getSession();
			session.setAttribute("actor", "user");
			String sessionId = session.getId();
			String addr = request.getRemoteAddr();
			System.out.println(addr+":"+sessionId);
			
			try {
				request.getRequestDispatcher("/success.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//request.getRequestDispatcher("/index.jsp").forward(request, response);
			try {
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
	}

}
