package com.rain.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rain.bean.AdminBean;
import com.rain.dao.AdminDao;

/**
 * Servlet实现类LoginServlet，用于处理管理员登录的请求。
 * 这段代码是一个Servlet，它的作用是处理管理员的登录请求。
 * 它通过AdminDao类与数据库进行交互，验证提交的用户名和密码是否匹配。
 * 如果验证成功，将管理员ID设置到会话中，并根据管理员的状态重定向到相应的页面；
 * 如果验证失败，则将错误信息设置到会话中，并重定向回登录页面。
 * 这个Servlet主要处理POST请求，GET请求将不会被处理。
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 默认构造函数。
	 */
	public LoginServlet() {
		super();
		// 构造函数中暂无代码，仅为了满足Servlet规范。
	}

	/**
	 * 处理GET请求的方法。
	 * 由于本Servlet主要处理POST请求，GET请求的处理方法为空。
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 暂无实现，GET请求不被处理。
	}

	/**
	 * 处理POST请求的方法。
	 * 从请求中获取管理员登录信息，并验证管理员身份。
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求和响应的编码类型为UTF-8，以支持国际化字符。
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 从请求中获取管理员的用户名和密码。
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// 创建AdminDao实例，用于数据库操作。
		AdminDao userdao = new AdminDao();

		// 调用AdminDao的Login_verify方法，验证用户名和密码是否正确。
		boolean result = userdao.Login_verify(username, password);

		// 获取当前会话对象。
		HttpSession session = request.getSession();

		if (result) {
			// 如果验证通过，创建AdminBean对象，用于存储管理员信息。
			AdminBean adminbean = new AdminBean();
			// 创建AdminDao实例，用于获取管理员详细信息。
			AdminDao admindao = new AdminDao();
			// 根据用户名和密码获取管理员详细信息。
			adminbean = admindao.getAdminInfo(username, password);
			// 将管理员ID存储到会话中，以便后续使用。
			session.setAttribute("aid", "" + adminbean.getAid());
			// 设置会话过期时间为6000秒（16分钟）。
			session.setMaxInactiveInterval(6000);
			// 根据管理员的状态重定向到不同的页面。
			if (adminbean.getStatus() == 1) {
				response.sendRedirect("/books/index.jsp");
			} else {
				response.sendRedirect("/books/admin.jsp");
			}
		} else {
			// 如果验证失败，设置错误信息到会话，并重定向到登录页面。
			session.setAttribute("state", "账号或密码错误");
			response.sendRedirect("/books/login.jsp");
		}
	}
}