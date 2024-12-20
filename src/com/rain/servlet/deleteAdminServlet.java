package com.rain.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.dao.AdminDao;

/**
 * Servlet实现类deleteUserServlet，用于处理删除管理员的请求。
 * 这段代码是一个Servlet，它的作用是处理删除管理员的请求。
 * 它通过AdminDao类与数据库进行交互，根据提交的管理员ID执行删除操作，
 * 并将用户重定向到管理员列表页面。
 * 这个Servlet主要处理GET请求，POST请求将直接调用GET方法进行处理。
 */
@WebServlet("/deleteAdminServlet")
public class deleteAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 默认构造函数。
	 */
	public deleteAdminServlet() {
		super();
		// 构造函数中暂无代码，仅为了满足Servlet规范。
	}

	/**
	 * 处理GET请求的方法。
	 * 根据请求参数执行删除管理员的操作。
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求和响应的编码类型为UTF-8，以支持国际化字符。
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 从请求中获取要删除的管理员ID参数。
		int aid = Integer.parseInt(request.getParameter("aid"));

		// 创建AdminDao实例，用于数据库操作。
		AdminDao admindao = new AdminDao();

		// 调用AdminDao的deleteAdmin方法，根据管理员ID删除管理员。
		admindao.deleteAdmin(aid);

		// 删除管理员后，重定向到管理员列表页面。
		response.sendRedirect("/books/admin_admin.jsp");
	}

	/**
	 * 处理POST请求的方法。
	 * 本Servlet主要处理GET请求，POST请求的处理方法为空，直接调用GET方法。
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}