 package com.master.util.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.util.Excecoes;
import com.master.util.OLUtil;

public class ComboServlet extends HttpServlet {

	/**
	 * serialVersionUID id para serializa��o
	 */
	private static final long serialVersionUID = -9193607220486941502L;
	
	/**
	 * Constructor of the object.
	 */
	public ComboServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 *
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			processa(request,response);
		} catch (Excecoes e) {
			e.printStackTrace();
		}

	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 *
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			processa(request,response);
		} catch (Excecoes e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
		
	}

	private void processa (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException, Excecoes {

		OLUtil OLUtil = new OLUtil();
		
		//Pega os par�metros do form
		String combo = request.getParameter("combo");
		String consulta = request.getParameter("consulta");
		String acao = "CB";
		String ed = combo+"ED";
		String rn = combo+"RN";
		// Se o combo � de consulta coloca CBC na acao para que no rn seja colocado o registro TODOS no primeiro registro do XML de retorno 
		if (consulta != null){
			acao = "CBC";
		}
		
		if (rn == null){
			response.setContentType("text/html");
	    	PrintWriter out = response.getWriter();
	    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
	    	out.println("<cad>");
	    	out.println("</cad>");
	    	out.flush();
	    	out.close();
		} else {
			//Cola os parametros no bean
			Object Obj = OLUtil.requestToBean(request,ed);
			//Executa o m�todo processaOL na classe rn
			OLUtil.processaOL(request, response, acao, rn, Obj);
		}
	}

	
}
